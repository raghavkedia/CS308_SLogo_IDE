package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.regex.Pattern;

public class testParsing {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	private static final String KEEP_END_LINE = "\\\\n";
	private static final String END_LINE_STRING = "\n";
	private static final String END_LINE = "\\n";
	private ResourceBundle myLanguageResources;
	private ResourceBundle mySyntaxResources;
	
	public testParsing() {
		
	}
	
	public void testMyParsing() {
		
		myLanguageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
		mySyntaxResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Syntax");
		String input = "[ Sum 10 20 ]";
		Collection<String> myStrings = cleanStrings(input.toLowerCase().replaceAll(END_LINE_STRING, KEEP_END_LINE).split("\\s+"));
		Collection<ExpressionNode> myNodes = convertToNodes(myStrings);
		double result = executeExpressions(myNodes);
		System.out.println(result);
	}

	public static void main(String[] args) {
		testParsing t = new testParsing();
		t.testMyParsing();
	}
	
	private void checkForBrackets(List<ExpressionNode> myNodes) {
		List<ExpressionNode> toRemove = new ArrayList<ExpressionNode>();	
		boolean unfinished = true;
		boolean foundForwardBracket = false;
		boolean foundBackwardBracket = false;
		while (unfinished) {
			unfinished = false;
			for (ExpressionNode node : myNodes) {
				if (node instanceof ForwardBracketNode) {
					foundForwardBracket = true;
					int index =  myNodes.indexOf(node);
					//check for errors with indexing
					for (int k = index + 1; k < myNodes.size(); k++) {
						ExpressionNode child = myNodes.get(k);
						if (!(child instanceof BackBracketNode)) {
							node.addChild(child);
							toRemove.add(child);
						}
						else {
							unfinished = true;
							foundBackwardBracket = true;
							toRemove.add(child);
							break;
						}
					}
				}
				if (foundForwardBracket != foundBackwardBracket) {
					//throw exception
				}
			}
			myNodes.removeAll(toRemove);
		}
		
	}
	
	public double executeExpressions(Collection<ExpressionNode> myNodes) {
		List<ExpressionNode> myNodeCopies = new ArrayList<ExpressionNode>(myNodes);
		checkForBrackets(myNodeCopies);
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = myNodeCopies.get(0);
		double result = 0;
		while (!myNodeCopies.isEmpty()) {
			myNodeCopies.remove(curr);
			if (isSatisfied(curr)) {
				if (myStack.isEmpty()) {
					result = curr.execute();
					curr = myNodeCopies.get(0);
				}
				else {
					ExpressionNode parent = myStack.pop();
					parent.addChild(curr);
					curr = parent;
				}
			}
			else {
				myStack.push(curr);
				curr = myNodeCopies.get(0);
			}
		}
		result = curr.execute();
		return result;
	}
	
	private boolean isSatisfied(ExpressionNode node) {
		return node.currentNumChildren() == node.getMyCommandType().numArgs();
	}
	
	private Collection<ExpressionNode> convertToNodes(Collection<String> myStrings) {
		ExpressionNodeFactory myNodeFactory = new ExpressionNodeFactory();
		Tokenizer myTokenizer = new Tokenizer("English");
		List<ExpressionNode> myNodes = new ArrayList<ExpressionNode>();
		for (String s : myStrings) {
			myNodes.add(myNodeFactory.createNode(myTokenizer.createToken(s)));
		}
		return myNodes;
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
}
