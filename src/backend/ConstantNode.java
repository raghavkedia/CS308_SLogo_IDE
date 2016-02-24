package backend;

import backend.ExpressionNode;

public class ConstantNode implements ExpressionNode{
	private double value;
	
	public ConstantNode(double value) {
		this.value = value;
	}

	public double execute() {
		return value;
	}

}