package backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
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
	public String runInput(String input, CharactersList myCharactersList, VariablesList myVariablesList, UserDefinedCommands myUserDefinedCommands, ResourceBundle myResources) throws Exception {
		Collection<String> myStrings = cleanStrings(input.toLowerCase().replaceAll(END_LINE_STRING, KEEP_END_LINE).split("\\s+"));
		CommandFactory myFactory = new CommandFactory(myCharactersList, myVariablesList, myUserDefinedCommands);
//		Collection<ExpressionNode> myNodes = convertToNodes(myStrings, myFactory);
//		Collection<ExpressionNode> cleanedNodes = checkForBrackets(myNodes);
//		LogoExpressionTreeBuilder myTreeBuilder = new LogoExpressionTreeBuilder();
//		double result = myTreeBuilder.executeExpressions(cleanedNodes);
//		String statement = "The result is " + result;
//		return statement;
		for (String s : myStrings) System.out.println(s);
		return input;
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
	
	private Collection<ExpressionNode> convertToNodes(Collection<String> myStrings, CommandFactory myFactory) {
		ExpressionNodeFactory myNodeFactory = new ExpressionNodeFactory(myFactory);
		Tokenizer myTokenizer = new Tokenizer(language);
		Collection<ExpressionNode> myNodes = new ArrayList<ExpressionNode>();
		for (String s : myStrings) {
			myNodes.add(myNodeFactory.createNode(myTokenizer.createToken(s)));
		}
		return myNodes;
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
	
	@Override
	public ParsedInput parse(String input) {
		// TODO Auto-generated method stub
		return null;
	}
}
