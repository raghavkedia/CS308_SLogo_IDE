package backend;

public class VariableNode implements ExpressionNode {

	public VariableNode() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double execute() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int currentNumChildren() {
		return 0;
	}

	@Override
	public Command getMyCommandType() {
		return Command.Variable;
	}

	@Override
	public void addChild(ExpressionNode n) {
		//throws exception
	}

}
