package backend;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import backend.ExpressionNodeFactory.NodeType;
import exceptions.InvalidCommandError;
import exceptions.SlogoError;
import exceptions.SyntaxError;

public class Tokenizer {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String SYNTAX = "Syntax";
	private ResourceBundle myLanguageResources;
	private ResourceBundle mySyntaxResources;
	private ResourceBundle myErrorResources;
	
	
	public Tokenizer(String language) {
		myLanguageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "languages/" + language);
		mySyntaxResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "languages/" + SYNTAX); 
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
	}
	
	public Token createToken(String s) throws SlogoError{
		if (Pattern.matches(mySyntaxResources.getString("Constant"), s)) {
			return new Token(NodeType.Constant, null, null, Double.valueOf(s));
		}
		else if (Pattern.matches(mySyntaxResources.getString("Variable"), s)) {
			return new Token(NodeType.Variable, null, s, 0);
		}
		else if (Pattern.matches(mySyntaxResources.getString("ListStart"), s)) {
			return new Token(NodeType.ListStart, null, null, 0);
		}
		else if (Pattern.matches(mySyntaxResources.getString("ListEnd"), s)) {
			return new Token(NodeType.ListEnd, null, null, 0);
		}
		else if (Pattern.matches(mySyntaxResources.getString("Command"), s)) {
			Enumeration<String> myKeys = myLanguageResources.getKeys();
			Command myCommand = null;
			while (myKeys.hasMoreElements()) {
				String myKey = myKeys.nextElement();
				if (Pattern.matches(myLanguageResources.getString(myKey), s)) {
					myCommand = Command.valueOf(myKey);
					break;
				}
			}
			if (myCommand == null) {
				throw new InvalidCommandError(myErrorResources.getString("InvalidCommand"));
				//check user commands
				//throw exception
			}
			return new Token(NodeType.Command, myCommand, null, 0);
			
		}
		throw new SyntaxError(myErrorResources.getString("InvalidSyntax"));
	}

}
