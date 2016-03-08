package backend.parser;

import java.util.Map;
import java.util.ResourceBundle;

import backend.data.UserDefinedCommands;
import backend.data.Variable;
import backend.data.VariablesList;
import exceptions.InvalidCommandError;

public class ExpressionNodeFactory {
	public enum NodeType{
		Command, UserCommand, Variable, Constant, ListStart, ListEnd;
	}
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private CommandFactory myFactory;
	private UserDefinedCommands userDefinedCommands;
	private ResourceBundle myErrorResources;
	
	public ExpressionNodeFactory(CommandFactory myFactory, UserDefinedCommands userDefinedCommands) {
		this.myFactory = myFactory;
		this.userDefinedCommands = userDefinedCommands;
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");

	}
	
	public ExpressionNode createNode(Token myToken) throws InvalidCommandError {
		NodeType myNode = myToken.getMyNodeType();
		Command myCommand = myToken.getMyCommand();
		String myName = myToken.getMyName();
		double myValue = myToken.getValue();
		if (myNode == NodeType.Command) {
			if (myCommand == Command.UserCommand) {
				//list maybe?
				return new UserCommandNode(myName, myFactory);
			}
			return new CommandNode(myCommand, myName, myFactory);
		} 
		else if (myNode == NodeType.Variable) {
			return new CommandNode(myCommand, myName, myFactory);
		}
		else if (myNode == NodeType.Constant) {
			return new ConstantNode(myValue);
		}
		else if (myNode == NodeType.ListStart) {
			return new ForwardBracketNode();
		}
		else if (myNode == NodeType.ListEnd) {
			return new BackBracketNode();
		}
		else if (myNode == NodeType.UserCommand) {
			UserCommandNode myUserCommandNode = (UserCommandNode) userDefinedCommands.getCommand(myName);
			if (myUserCommandNode != null) {
				myUserCommandNode.activateCommand();
				myUserCommandNode.getMyChildren().clear();
				return myUserCommandNode; 
			}
			throw new InvalidCommandError(myErrorResources.getString("InvalidCommand"));				
		}
		return null;
	}
}
