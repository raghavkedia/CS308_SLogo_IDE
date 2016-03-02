package backend;

import java.util.Collection;
import java.util.List;

import exceptions.SlogoError;

public class MakeVariableNode implements ExpressionNode {
	private Command type;
	private List<ExpressionNode> myChildren;
	
	public MakeVariableNode(Command type) {
		this.type = type;
	}

	@Override
	public double execute() throws SlogoError {
		ExpressionNode myChild = myChildren.get(0);
		if (myChild instanceof VariableNode) {
			if (((VariableNode) myChild).getMyVariable() == null) {
				((VariableNode) myChild).makeMyVariable();
			} 
		} else {
			//throw error
		}
		return myChild.execute();
	}

	@Override
	public int currentNumChildren() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Command getMyCommandType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addChild(ExpressionNode n) {
		// TODO Auto-generated method stub
		
	}

}
