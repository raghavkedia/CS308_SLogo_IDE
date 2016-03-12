package backend.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import backend.data.Data;
import exceptions.SlogoError;

public class SimpleSplitParse implements Parseable {
	private static final String RESULT_STATEMENT = "The result is ";
	private static final String KEEP_END_LINE = "\\\\n";
	private static final String END_LINE_STRING = "\n";
	private static final String END_LINE = "\\n";
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	public static final String SYNTAX = "Syntax";
	private String language;

	public SimpleSplitParse(String language) {
		this.language = language;
		ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SYNTAX);
	}

	public Collection<String> stringParse(String input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String runInput(String input, Data myData, ResourceBundle myResources) throws SlogoError {
		input = input.replaceAll(END_LINE_STRING, END_LINE_STRING + " ");
		List<String> myStrings = cleanStrings(input.toLowerCase().replaceAll(END_LINE_STRING, KEEP_END_LINE).split("\\s+"));
		CommandFactory myFactory = new CommandFactory(myData.getCharacterList(), myData.getVariablesList(), myData.getUserDefinedCommands(), 
				myData.getProperties(), myData.getColorMap(), myData.getShapeMap());
		LogoExpressionTreeBuilder myTreeBuilder = new LogoExpressionTreeBuilder(language, myFactory, myData.getUserDefinedCommands());
		double result = 0;
		while (!myStrings.isEmpty()) {
			result = myTreeBuilder.executeExpression(myStrings);
		}
		String statement = RESULT_STATEMENT + result;
		return statement;
	}

	private List<String> cleanStrings(String[] mySplitString) {
		boolean foundComment = false;
		for (int k = 0; k < mySplitString.length; k++) {
			if (Pattern.matches("#", mySplitString[k])) {
				foundComment = true;
			}
			if (foundComment && mySplitString[k].contains(END_LINE)) {
				mySplitString[k] = "";
				foundComment = false;
			}
			if (foundComment) {
				mySplitString[k] = "";
			}
			if (mySplitString[k].contains(END_LINE)) {
				mySplitString[k] = mySplitString[k].replace(END_LINE, "");
			}

		}
		List<String> myStrings = new ArrayList<String>(Arrays.asList(mySplitString));
		myStrings.removeIf(p -> p.equals(""));
		return myStrings;
	}

}
