package backend.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.SlogoError;

public class MakeVariableNode implements ExpressionNode {
	private Command type;
	private List<ExpressionNode> myChildren;
	
	public MakeVariableNode(Command type) {
		this.type = type;
		this.myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public double execute() throws SlogoError {
		ExpressionNode myChild = myChildren.get(0);
		if (myChild instanceof VariableNode) {
			((VariableNode) myChild).makeMyVariable();
		} else {
			//throw error
		}
		return myChild.execute();
	}

	@Override
	public int currentNumChildren() {
		return myChildren.size();
	}

	@Override
	public Command getMyCommandType() {
		return type;
	}

	@Override
	public void addChild(ExpressionNode n) {
		myChildren.add(n);
	}

}
