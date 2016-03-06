package backend.parser;

import backend.data.Variable;
import backend.data.VariablesList;

public class ExpressionNodeFactory {
	public enum NodeType{
		Command, Variable, Constant, ListStart, ListEnd;
	}
	private CommandFactory myFactory;
	private VariablesList myVariablesList;
	
	public ExpressionNodeFactory(CommandFactory myFactory, VariablesList myVariablesList) {
		this.myFactory = myFactory;
		this.myVariablesList = myVariablesList;
	}
	
	public ExpressionNode createNode(Token myToken) {
		NodeType myNode = myToken.getMyNodeType();
		Command myCommand = myToken.getMyCommand();
		String myName = myToken.getMyName();
		double myValue = myToken.getValue();
		if (myNode == NodeType.Command) {
//			//check for the special ones.
//			if (myCommand == Command.If || myCommand == Command.IfElse) {
//				System.out.println("hit");
//				return new ConditionNode(myCommand, myFactory);
//			} else if (myCommand == Command.MakeVariable) {
//				return new MakeVariableNode(myCommand);
//			}
			return new CommandNode(myCommand, myName, myFactory);
		} 
		else if (myNode == NodeType.Variable) {
//			Variable myVariable = myVariablesList.getVariable(myName);
//			if (myVariable == null) {
//				myVariable = new Variable(myName, null);
//				myVariablesList.addVariable(myVariable);
//			}
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
		return null;
	}
}
