package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
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
		String input = "Sum Sum Sum Sum 10 20 30 5 5";
		Collection<String> myStrings = cleanStrings(input.toLowerCase().replaceAll(END_LINE_STRING, KEEP_END_LINE).split("\\s+"));
		List<ExpressionNode> myNodes = convertToNodes(myStrings);
	}

	public static void main(String[] args) {
		testParsing t = new testParsing();
		t.testMyParsing();
	}
	
	private List<ExpressionNode> convertToNodes(Collection<String> myStrings) {
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
