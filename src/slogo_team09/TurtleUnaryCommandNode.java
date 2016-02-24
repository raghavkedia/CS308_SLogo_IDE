package slogo_team09;

public class TurtleUnaryCommandNode implements ExpressionNode {
	Command type;
	ExpressionNode child;
	Result result;
	
	public TurtleUnaryCommandNode(Command type) {
		this.type = type;
		result = new GeneralResult();
	}

	@Override
	public Result execute() {
		
		return result;
	}

}
