package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
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
	
	public void testMyParsing() throws Exception{
		myLanguageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "English");
		mySyntaxResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Syntax");
		String input = "Sum Sum Sum Sum 10 20 30 5 5";//"[ Sum Sum Sum Sum 10 20 30 5 5 ]";
		Collection<String> myStrings = cleanStrings(input.toLowerCase().replaceAll(END_LINE_STRING, KEEP_END_LINE).split("\\s+"));
		Collection<ExpressionNode> myNodes = convertToNodes(myStrings);
		Collection<ExpressionNode> cleanNodes = checkForBrackets(myNodes);
		try{
			double result = executeExpressions(cleanNodes);
			System.out.println(result);
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}
		
	}

	public static void main(String[] args) {
		testParsing t = new testParsing();
		try {
			t.testMyParsing();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());;
		}
		System.out.println("MEOW");
	}
	
	private Collection<ExpressionNode> checkForBrackets(Collection<ExpressionNode> myCurrentNodes) {
		List<ExpressionNode> toRemove = new ArrayList<ExpressionNode>();
		List<ExpressionNode> myNodes = new ArrayList<ExpressionNode>(myCurrentNodes);
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
		return myNodes;
	}
	
	public double executeExpressions(Collection<ExpressionNode> myNodes) throws Exception{
		List<ExpressionNode> myNodeCopies = new ArrayList<ExpressionNode>(myNodes);
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = myNodeCopies.get(0);
		List<ExpressionNode> toExecute = new ArrayList<ExpressionNode>();
		double result = 0;
		while (!myNodeCopies.isEmpty()) {
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
				curr = myNodeCopies.get(0);
			}
		}
		if (!toExecute.contains(curr)) {
			toExecute.add(curr);
		}
		for (ExpressionNode node : toExecute) {
			try{
				result = node.execute();
			}
			catch(Exception e){
				throw new Exception(e.getMessage());
			}
		}
		return result;
	}
	
	private boolean isSatisfied(ExpressionNode node) {
		return node.currentNumChildren() >= node.getMyCommandType().numArgs();
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
