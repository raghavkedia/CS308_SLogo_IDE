package backend.parser;

import backend.parser.ExpressionNode;

public class ConstantNode implements ExpressionNode{
	private double value;
	
	public ConstantNode(double value) {
		this.value = value;
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