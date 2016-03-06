package backend.parser;

import java.util.List;

import backend.parser.ExpressionNode;

public class ConstantNode implements ExpressionNode{
	private double value;
	
	public ConstantNode(double value) {
		this.value = value;
	}

	@Override
	public double execute() {
		System.out.println(getMyName());
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

	@Override
	public String getMyName() {
		return String.valueOf(value);
	}

	@Override
	public List<ExpressionNode> getMyChildren() {
		return null;
	}

}