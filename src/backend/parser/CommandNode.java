package backend.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.SlogoError;

public class CommandNode implements ExpressionNode {
	private Command type;
	private List<ExpressionNode> myChildren;
	private CommandFactory myFactory;
	
	public CommandNode(Command type, CommandFactory myFactory) {
		this.type = type;
		this.myFactory = myFactory;
		this.myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public double execute() throws SlogoError {
//		List<Double> executed = new ArrayList<Double>();
//		for (ExpressionNode n : myChildren) {
//			double r = n.execute();
//			executed.add(r);
//		}
		double result = myFactory.generateResult(type, myChildren);
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
