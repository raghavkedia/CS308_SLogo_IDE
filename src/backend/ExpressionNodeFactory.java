package backend;

public class ExpressionNodeFactory {
	public enum NodeType{
		Command, Variable, Constant;
	}
	
	public ExpressionNodeFactory() {
		
	}
	
	public ExpressionNode createNode(Token myToken) {
		NodeType myNode = myToken.getMyNodeType();
		Command myCommand = myToken.getMyCommand();
		double myValue = myToken.getValue();
		if (myNode == NodeType.Command) {
			return new CommandNode(myCommand);
		} 
		else if (myNode == NodeType.Variable) {
			return new VariableNode();
		}
		else if (myNode == NodeType.Constant) {
			return new ConstantNode(myValue);
		}
		return null;
	}
}
