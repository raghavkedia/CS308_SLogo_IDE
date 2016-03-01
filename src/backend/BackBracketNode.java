package backend;

public class BackBracketNode implements ExpressionNode{

	public BackBracketNode() {
		
	}

	@Override
	public double execute() {
		return 0;
	}

	@Override
	public int currentNumChildren() {
		return 0;
	}

	@Override
	public Command getMyCommandType() {
		return null;
	}

	@Override
	public void addChild(ExpressionNode n) {

	}

}
