package backend.parser;

import java.util.Map;

import backend.data.UserDefinedCommands;
import backend.data.Variable;
import backend.data.VariablesList;
import resources.languages.UserCommandNode;

public class ExpressionNodeFactory {
	public enum NodeType{
		Command, UserCommand, Variable, Constant, ListStart, ListEnd;
	}
	private CommandFactory myFactory;
	private UserDefinedCommands userDefinedCommands;
	
	public ExpressionNodeFactory(CommandFactory myFactory, UserDefinedCommands userDefinedCommands) {
		this.myFactory = myFactory;
		this.userDefinedCommands = userDefinedCommands;
	}
	
	public ExpressionNode createNode(Token myToken) {
		NodeType myNode = myToken.getMyNodeType();
		Command myCommand = myToken.getMyCommand();
		String myName = myToken.getMyName();
		double myValue = myToken.getValue();
		if (myNode == NodeType.Command) {
			if (myCommand == Command.UserCommand) {
				return new UserCommandNode(myName);
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
			userDefinedCommands.getCommand(myName);
		}
		return null;
	}
}
