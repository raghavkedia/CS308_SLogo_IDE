package backend;

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
			//check for the special ones.
			return new CommandNode(myCommand, myFactory);
		} 
		else if (myNode == NodeType.Variable) {
			Variable myVariable = new Variable(myName , null);
			myVariablesList.addVariable(myVariable);
			return new VariableNode(myVariable);
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
