package backend.parser;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import backend.data.UserDefinedCommands;
import exceptions.SlogoError;
import exceptions.TooFewParametersError;

//This entire file is part of my masterpiece.
//Christine Zhou
/*
 * This class gets a string of inputs, uses the tokenizer to convert the strings to nodes one at a time,
 * and builds the tree a node at a time, converting strings to nodes as necessary. This allows executable
 * parts of the input to execute first without having to wait for the trees from the other parts of the 
 * input to finish building. This is part of my masterpiece because the entire tree building process 
 * happens within this class. This class also hides the tree building process from the parser and only sends
 * back the parser the result. The parser does not have to deal with how the expression tree is generated
 * and this class does not have to deal with how strings are tokenized then turned into nodes because
 * of other classes. This way if the implementation of the tokenizer, tree builder, etc I mentioned had to change to 
 * another implementation, it should be able to do so without modifying any of the other components. 
 */
public class LogoExpressionTreeBuilder implements ExpressionTreeBuilder {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myErrorResources;
	private UserDefinedCommands myUserDefinedCommands;
	private Tokenizer myTokenizer;
	private ExpressionNodeFactory myNodeFactory;

	public LogoExpressionTreeBuilder(String language, CommandFactory myFactory,
			UserDefinedCommands userDefinedCommands) {
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
		myUserDefinedCommands = userDefinedCommands;
		myTokenizer = new Tokenizer(language);
		myNodeFactory = new ExpressionNodeFactory(myFactory, myUserDefinedCommands);
	}

	/*
	 * Executes expressions based on a list of strings. Will stop building tree
	 * as soon as it is possible.
	 */
	public SLogoTreeResult executeExpression(List<String> myStrings) throws SlogoError {
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = convertToNode(getNextStringAndRemoveFromList(myStrings));
		curr = buildTree(myStrings, myStack, curr);
		return new SLogoTreeResult(curr, curr.execute());
	}

	private ExpressionNode buildTree(List<String> myStrings, Stack<ExpressionNode> myStack, ExpressionNode curr)
			throws SlogoError, TooFewParametersError {
		while (true) {
			myStrings.remove(curr);
			if (isSatisfied(curr)) {
				if (myStack.isEmpty()) {
					curr = getNextNode(myStrings, curr);
				} else {
					ExpressionNode parent = myStack.pop();
					parent.addChild(curr);
					curr = parent;
				}
			} else {
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
		if (!myStrings.isEmpty()) {
			curr = convertToNode(getNextStringAndRemoveFromList(myStrings));
		}
		return curr;
	}

	private String getNextStringAndRemoveFromList(List<String> myStrings) {
		String currentString = myStrings.get(0);
		myStrings.remove(currentString);
		return currentString;
	}

	private ExpressionNode convertToNode(String myString) throws SlogoError {
		return myNodeFactory.createNode(myTokenizer.createToken(myString));
	}

	private boolean isSatisfied(ExpressionNode node) {
		return node.currentNumChildren() >= node.getMyCommandType().numArgs();
	}
}