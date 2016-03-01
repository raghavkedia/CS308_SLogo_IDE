package backend;

public class ExpressionNodeFactory {
	public enum NodeType{
		Command, Variable, Constant, ListStart, ListEnd;
	}
	private CommandFactory myFactory;
	
	public ExpressionNodeFactory(CommandFactory myFactory, VariablesList myVariablesList) {
		this.myFactory = myFactory;
	}
	
	public ExpressionNode createNode(Token myToken) {
		NodeType myNode = myToken.getMyNodeType();
		Command myCommand = myToken.getMyCommand();
		double myValue = myToken.getValue();
		if (myNode == NodeType.Command) {
			//check for the special ones.
			return new CommandNode(myCommand, myFactory);
		} 
		else if (myNode == NodeType.Variable) {
			return new VariableNode();
		}
		else if (myNode == NodeType.Constant) {
			return new ConstantNode(myValue);
		}
		else if (myNode == NodeType.Variable) {
			return new VariableNode();
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
