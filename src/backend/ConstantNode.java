package backend;

import backend.ExpressionNode;

public class ConstantNode implements ExpressionNode{
	private double value;
	private Result result;
	
	public ConstantNode(double value) {
		this.value = value;
		result = new GeneralResult();
	}

	@Override
	public double execute() {
		return value;
	}

	@Override
	public int currentNumChildren() {
		return 0;
	}

	@Override
	public Command getMyCommandType() {
		return Command.Constant;
	}

	@Override
	public void addChild(ExpressionNode n) {
		//throw exception
	}

}