package backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommandNode implements ExpressionNode {
	private Command type;
	private Collection<ExpressionNode> myChildren;
	
	public CommandNode(Command type) {
		this.type = type;
		this.myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public double execute() {
		List<Double> executed = new ArrayList<Double>();
		for (ExpressionNode n : myChildren) {
			double r = n.execute();
			executed.add(r);
		}
		CommandFactory myFactory = new CommandFactory();
		double result = myFactory.generateResult(type, executed);
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
