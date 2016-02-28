package backend;

public class ExpressionNodeFactory {
	public enum NodeType{
		Command, Variable, Constant;
	}
	
	public ExpressionNodeFactory() {
		
	}
	
	public ExpressionNode createNode(NodeType myNode, Command myCommand, double myValue) {
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
