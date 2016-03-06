package backend.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.SlogoError;

public class CommandNode implements ExpressionNode {
	private Command type;
	String myName;
	private List<ExpressionNode> myChildren;
	private CommandFactory myFactory;
	
	public CommandNode(Command type, String myName, CommandFactory myFactory) {
		this.type = type;
		this.myName = myName;
		this.myFactory = myFactory;
		this.myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public double execute() throws SlogoError {
		double result = myFactory.generateResult(type, myName, myChildren, null);
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

	@Override
	public String getMyName() {
		return myName;
	}

	@Override
	public List<ExpressionNode> getMyChildren() {
		return myChildren;
	}

}
