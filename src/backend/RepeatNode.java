package backend;

import java.util.ArrayList;
import java.util.List;

import exceptions.SlogoError;

public class RepeatNode implements ExpressionNode{
	private Command type;
	private List<ExpressionNode> myChildren;
	private CommandFactory myFactory;
	
	public RepeatNode(Command type, CommandFactory myFactory) {
		this.type = type;
		this.myFactory = myFactory;
		this.myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public double execute() throws SlogoError {
		double times = myChildren.get(0).execute();
		double result = 0;
		int repeatTimes = (int) Math.floor(times);
		for (int k = 0; k < repeatTimes; k++) {
			result = myChildren.get(1).execute();
		}
		return result;
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
