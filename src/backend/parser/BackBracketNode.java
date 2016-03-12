package backend.parser;

import java.util.List;

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
		return Command.LISTEND;
	}

	@Override
	public void addChild(ExpressionNode n) {

	}

	@Override
	public String getMyName() {
		return "]";
	}

	@Override
	public List<ExpressionNode> getMyChildren() {
		return null;
	}

}
