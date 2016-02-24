package slogo_team09;

import slogo_team09.ExpressionNode;

public class ConstantNode implements ExpressionNode{
	private double value;
	private Result result;
	
	public ConstantNode(double value) {
		this.value = value;
		result = new GeneralResult();
	}

	@Override
	public Result execute() {
		result.setMyDouble(value);
		return result;
	}

}