package backend.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import backend.data.CharactersList;
import backend.data.Data;
import backend.data.UserDefinedCommands;
import backend.data.VariablesList;
import exceptions.SlogoError;

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
	public String runInput(String input, Data myData, ResourceBundle myResources) throws SlogoError {
		input = input.replaceAll(END_LINE_STRING, END_LINE_STRING + " ");
		Collection<String> myStrings = cleanStrings(input.toLowerCase().replaceAll(END_LINE_STRING, KEEP_END_LINE).split("\\s+"));
		CommandFactory myFactory = new CommandFactory(myData.getCharacterList(), myData.getVariablesList(), myData.getUserDefinedCommands());
		Collection<ExpressionNode> myNodes = convertToNodes(myStrings, myFactory, myData.getUserDefinedCommands());
		LogoExpressionTreeBuilder myTreeBuilder = new LogoExpressionTreeBuilder();
		double result = myNodes.size() != 0 ? myTreeBuilder.executeExpressions(myNodes) : 0;
		String statement = "The result is " + result;
		return statement;
	}

	private Collection<String> cleanStrings(String[] mySplitString) {
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
		Collection<String> myStrings = new ArrayList<String>(Arrays.asList(mySplitString));
		myStrings.removeIf(p -> p.equals(""));
		return myStrings;
	}

	private Collection<ExpressionNode> convertToNodes(Collection<String> myStrings, CommandFactory myFactory, UserDefinedCommands userDefinedCommands) throws SlogoError{
		ExpressionNodeFactory myNodeFactory = new ExpressionNodeFactory(myFactory, userDefinedCommands);
		Tokenizer myTokenizer = new Tokenizer(language);
		Collection<ExpressionNode> myNodes = new ArrayList<ExpressionNode>();
		for (String s : myStrings) {
			myNodes.add(myNodeFactory.createNode(myTokenizer.createToken(s)));
		}
		return myNodes;
	}
}
