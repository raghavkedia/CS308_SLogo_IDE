package backend.parser;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import backend.data.UserDefinedCommands;
import exceptions.SlogoError;
import exceptions.TooFewParametersError;

public class LogoExpressionTreeBuilder implements ExpressionTreeBuilder {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myErrorResources;
	private CommandFactory myFactory;
	private UserDefinedCommands myUserDefinedCommands;
	private Tokenizer myTokenizer;
	
	public LogoExpressionTreeBuilder(String language, CommandFactory myFactory, UserDefinedCommands userDefinedCommands) {
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
		this.myFactory = myFactory;
		myUserDefinedCommands = userDefinedCommands;
		myTokenizer = new Tokenizer(language);
	}
	
	/*
	 * Executes expressions based on a list of strings. Will stop building tree as soon as it is possible.
	 */
	public double executeExpression(List<String> myStrings) throws SlogoError {
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = convertToNode(getNextStringAndRemoveFromList(myStrings));
		curr = buildTree(myStrings, myStack, curr);
		return curr.execute();
	}

	private ExpressionNode buildTree(List<String> myStrings, Stack<ExpressionNode> myStack, ExpressionNode curr)
			throws SlogoError, TooFewParametersError {
		while (true) {
			myStrings.remove(curr);
			if (isSatisfied(curr)) {
				if (myStack.isEmpty()) {
					curr = getNextNode(myStrings, curr);
				}
				else {
					ExpressionNode parent = myStack.pop();
					parent.addChild(curr);
					curr = parent;
				}
			}
			else {
				myStack.push(curr);
				if (myStrings.isEmpty() && !myStack.isEmpty()) {
					throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
				}
				curr = getNextNode(myStrings, curr);
			}
			if (myStack.isEmpty() && isSatisfied(curr)) {
				break;
			}
		}
		return curr;
	}

	private ExpressionNode getNextNode(List<String> myStrings, ExpressionNode curr) throws SlogoError {
		if(!myStrings.isEmpty()){
			curr = convertToNode(getNextStringAndRemoveFromList(myStrings));
		}
		return curr;
	}

	private String getNextStringAndRemoveFromList(List<String> myStrings) {
		String currentString = myStrings.get(0);
		myStrings.remove(currentString);
		return currentString;
	}
	
	private ExpressionNode convertToNode(String myString) throws SlogoError{
		ExpressionNodeFactory myNodeFactory = new ExpressionNodeFactory(myFactory, myUserDefinedCommands);
		return myNodeFactory.createNode(myTokenizer.createToken(myString));
	}
	
	private boolean isSatisfied(ExpressionNode node) {
		return node.currentNumChildren() >= node.getMyCommandType().numArgs();
	}
}