package backend;

import java.util.ResourceBundle;
import java.util.regex.Pattern;

import backend.ExpressionNodeFactory.NodeType;

public class Tokenizer {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	public static final String SYNTAX = "Syntax";
	private ResourceBundle myLanguageResources;
	private ResourceBundle mySyntaxResources;
	
	public Tokenizer(String language) {
		myLanguageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + language);
		mySyntaxResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + SYNTAX); 
	}
	
	public Token createToken(String s) {
		if (Pattern.matches(mySyntaxResources.getString("Constant"), s)) {
			return new Token(NodeType.Constant, null, Double.valueOf(s));
		}
		else if (Pattern.matches(mySyntaxResources.getString("Variable"), s)) {
			return new Token(NodeType.Variable, null, 0);
		}
		else if (Pattern.matches(mySyntaxResources.getString("Command"), s)) {
			return new Token(NodeType.Command, Command.valueOf(myLanguageResources.getString(s)), 0);
		}
		return null;
	}

}
