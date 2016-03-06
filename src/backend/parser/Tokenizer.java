package backend.parser;

import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import backend.parser.ExpressionNodeFactory.NodeType;
import exceptions.InvalidCommandError;
import exceptions.SlogoError;
import exceptions.SyntaxError;

public class Tokenizer {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	public static final String SYNTAX = "Syntax";
	private ResourceBundle myLanguageResources;
	private ResourceBundle mySyntaxResources;
	private ResourceBundle myErrorResources;
	private Token previousToken;
	
	public Tokenizer(String language) {
		myLanguageResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "languages/" + language);
		mySyntaxResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "languages/" + SYNTAX); 
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
	}
	
	public Token createToken(String s) throws SlogoError{
		if (Pattern.matches(mySyntaxResources.getString("Constant"), s)) {
			previousToken = new Token(NodeType.Constant, null, s, Double.valueOf(s));
			return previousToken;
		}
		else if (Pattern.matches(mySyntaxResources.getString("Variable"), s)) {
			previousToken = new Token(NodeType.Variable, Command.Variable, s, 0);
			return previousToken;
		}
		else if (Pattern.matches(mySyntaxResources.getString("ListStart"), s)) {
			previousToken = new Token(NodeType.ListStart, null, null, 0);
			return previousToken;
		}
		else if (Pattern.matches(mySyntaxResources.getString("ListEnd"), s)) {
			previousToken = new Token(NodeType.ListEnd, null, null, 0);
			return previousToken;
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
				if (previousToken.getMyCommand() == Command.MakeUserInstruction) {
					new Token(NodeType.Command, Command.UserCommand, s, 0);
				}
				else {
					throw new InvalidCommandError(myErrorResources.getString("InvalidCommand"));					
				}
				//check user commands
				//throw exception
			}
			previousToken = new Token(NodeType.Command, myCommand, s, 0);
			return previousToken; 
			
		}
		throw new SyntaxError(myErrorResources.getString("SyntaxError"));
	}

}
