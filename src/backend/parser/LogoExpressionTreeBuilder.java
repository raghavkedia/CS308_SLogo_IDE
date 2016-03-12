package backend.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import backend.data.UserDefinedCommands;
import exceptions.SlogoError;
import exceptions.TooFewParametersError;

public class LogoExpressionTreeBuilder implements ExpressionTreeBuilder {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myErrorResources;
	private CommandFactory myFactory;
	private UserDefinedCommands myUserDefinedCommands;
	private String language;
	private Tokenizer myTokenizer;
	
	public LogoExpressionTreeBuilder(String language, CommandFactory myFactory, UserDefinedCommands userDefinedCommands) {
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
		this.myFactory = myFactory;
		this.language = language;
		myUserDefinedCommands = userDefinedCommands;
		myTokenizer = new Tokenizer(language);
	}
	
	public double executeExpressions(Collection<ExpressionNode> myNodes) throws SlogoError {
		List<ExpressionNode> myNodeCopies = new ArrayList<ExpressionNode>(myNodes);
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = myNodeCopies.get(0);
		List<ExpressionNode> toExecute = new ArrayList<ExpressionNode>();
		double result = 0;
		while (!myNodeCopies.isEmpty() || !myStack.empty()) {
			myNodeCopies.remove(curr);
			if (isSatisfied(curr)) {
				if (myStack.isEmpty()) {
					if (!toExecute.contains(curr)) {
						toExecute.add(curr);
					}
					if(!myNodeCopies.isEmpty()) {
						curr = myNodeCopies.get(0);
					}
				}
				else {
					ExpressionNode parent = myStack.pop();
					parent.addChild(curr);
					curr = parent;
				}
			}
			else {
				myStack.push(curr);
				if(!myNodeCopies.isEmpty()){
					curr = myNodeCopies.get(0);
				}
				else if (myNodeCopies.isEmpty() && !myStack.isEmpty()) {
					throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
				}
			}
		}
		if (!toExecute.contains(curr)) {
			toExecute.add(curr);
		}
		for (ExpressionNode node : toExecute) {
			result = node.execute();
		}
		return result;
	}
	
	public double executeExpression(List<String> myStrings) throws SlogoError {
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = convertToNode(getNextStringAndRemoveFromList(myStrings));
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
		return curr.execute();
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