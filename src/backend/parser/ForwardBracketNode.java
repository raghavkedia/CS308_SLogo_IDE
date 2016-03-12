package backend.parser;

import java.util.ArrayList;
import java.util.List;

import exceptions.SlogoError;

public class ForwardBracketNode implements ExpressionNode {
	private List<ExpressionNode> myChildren;
	
	public ForwardBracketNode() {
		myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public double execute() throws SlogoError {
		double result = 0;
		for (ExpressionNode n : myChildren) {
			if (!(n instanceof BackBracketNode)) {
				result = n.execute();
			}
		}
		return result;			
	}

	@Override
	public int currentNumChildren() {
		int num = -1;
		for (ExpressionNode n : myChildren) {
			if (n instanceof BackBracketNode) {
				num = 0;
			}
		}
		return num;
	}

	@Override
	public Command getMyCommandType() {
		return Command.LISTSTART;
	}

	@Override
	public void addChild(ExpressionNode n) {
		myChildren.add(n);
	}

	@Override
	public String getMyName() {
		return "[";
	}

	@Override
	public List<ExpressionNode> getMyChildren() {
		return myChildren;
	}

}
