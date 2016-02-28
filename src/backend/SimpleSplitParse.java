package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;

public class SimpleSplitParse implements Parseable {
	private static final String KEEP_END_LINE = "\\\\n";
	private static final String END_LINE_STRING = "\n";
	private static final String END_LINE = "\\n";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	public static final String SYNTAX = "Syntax";
	private ResourceBundle myLanguageResources;
	private ResourceBundle mySyntaxResources;
	private String language;
	
	public SimpleSplitParse(String language) {
		this.language = language;
		myLanguageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		mySyntaxResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SYNTAX);
	}

	public Collection<String> stringParse(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String runInput(String input, CharactersList myCharactersList, VariablesList myVariablesList, ResourceBundle myResources) {
		Collection<String> myStrings = cleanStrings(input.toLowerCase().replaceAll(END_LINE_STRING, KEEP_END_LINE).split("\\s+"));
		List<ExpressionNode> myNodes = convertToNodes(myStrings);
		double result = executeExpressions(myNodes);
		String statement = "The result is " + result;
		return statement;
	}

	private Collection<String> cleanStrings(String[] mySplitString) {
		boolean foundComment = false;
		for (int k = 0; k < mySplitString.length; k++) {
			if (Pattern.matches(mySyntaxResources.getString("Comment"), mySplitString[k])) {
				foundComment = true;
			}
			if (foundComment && mySplitString[k].contains(END_LINE)) {
				mySplitString[k] = "";
				foundComment = false;
			}
			if (foundComment) {
				mySplitString[k] = "";
			}
				
		}
		Collection<String> myStrings = new ArrayList<String>(Arrays.asList(mySplitString));
		myStrings.removeIf(p -> p.equals(""));
		return myStrings;
	}
	
	private List<ExpressionNode> convertToNodes(Collection<String> myStrings) {
		ExpressionNodeFactory myNodeFactory = new ExpressionNodeFactory();
		Tokenizer myTokenizer = new Tokenizer(language);
		List<ExpressionNode> myNodes = new ArrayList<ExpressionNode>();
		for (String s : myStrings) {
			myNodes.add(myNodeFactory.createNode(myTokenizer.createToken(s)));
		}
		return myNodes;
	}

	private double executeExpressions(List<ExpressionNode> myNodes) {
		List<ExpressionNode> myNodeCopies = new ArrayList<ExpressionNode>(myNodes);
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = myNodeCopies.get(0);
		double result = 0;
		while (!myNodeCopies.isEmpty()) {
			if (isSatisfied(curr)) {
				myNodeCopies.remove(curr);
				if (myStack.isEmpty()) {
					//throws an exception for not enough arguments
				}
				else {
					ExpressionNode parent = myStack.pop();
					parent.addChild(curr);
					if (myStack.isEmpty()) {
						result = parent.execute();
					}
					else {
						curr = parent;
					}
				}
			}
			else {
				myStack.push(curr);
				curr = myNodeCopies.get(0);
			}
		}
		return result;
	}
	
	private boolean isSatisfied(ExpressionNode node) {
		return node.currentNumChildren() == node.getMyCommandType().numArgs();
	}
	
	@Override
	public ParsedInput parse(String input) {
		// TODO Auto-generated method stub
		return null;
	}
}
