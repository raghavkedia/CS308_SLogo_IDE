package slogo_team09;

import slogo_team09.ExpressionNode;

public class ConstantNode implements ExpressionNode{
	private double value;
	
	public ConstantNode(double value) {
		this.value = value;
	}

	@Override
	public double execute() {
		return value;
	}

}