package backend;

public class VariableNode implements ExpressionNode {
	private Variable myVariable;
	
	public VariableNode(Variable myVariable) {
		
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
