package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class SimpleSplitParse implements Parseable {
	private static final String KEEP_END_LINE = "\\\\n";
	private static final String END_LINE_STRING = "\n";
	private static final String END_LINE = "\\n";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	public static final String SYNTAX = "Syntax";
	private ResourceBundle mySyntaxResources;
	private String language;
	
	public SimpleSplitParse(String language) {
		this.language = language;
		mySyntaxResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SYNTAX);
	}

	public Collection<String> stringParse(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String runInput(String input, CharactersList myCharactersList, VariablesList myVariablesList, UserDefinedCommands myUserDefinedCommands, ResourceBundle myResources) {
		Collection<String> myStrings = cleanStrings(input.toLowerCase().replaceAll(END_LINE_STRING, KEEP_END_LINE).split("\\s+"));
		Collection<ExpressionNode> myNodes = convertToNodes(myStrings);
		LogoExpressionTreeBuilder myTreeBuilder = new LogoExpressionTreeBuilder();
		double result = myTreeBuilder.executeExpressions(myNodes);
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
	
	private Collection<ExpressionNode> convertToNodes(Collection<String> myStrings) {
		ExpressionNodeFactory myNodeFactory = new ExpressionNodeFactory();
		Tokenizer myTokenizer = new Tokenizer(language);
		Collection<ExpressionNode> myNodes = new ArrayList<ExpressionNode>();
		for (String s : myStrings) {
			myNodes.add(myNodeFactory.createNode(myTokenizer.createToken(s)));
		}
		return myNodes;
	}
	
	@Override
	public ParsedInput parse(String input) {
		// TODO Auto-generated method stub
		return null;
	}
}
