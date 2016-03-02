package backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.SlogoError;

public class ConditionNode implements ExpressionNode{
	private Command type;
	private List<ExpressionNode> myChildren;
	private CommandFactory myFactory;
	
	public ConditionNode(Command type, CommandFactory myFactory) {
		this.type = type;
		this.myFactory = myFactory;
		myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public double execute() throws SlogoError {
		System.out.println("0000");
		double condition = myChildren.get(0).execute();
		if (type == Command.If) {
			return condition != 0 ? myChildren.get(1).execute() : 0;
		} else if (type == Command.IfElse) {
			return condition != 0 ? myChildren.get(0).execute() : myChildren.get(1).execute();
		}
		return 0;
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
