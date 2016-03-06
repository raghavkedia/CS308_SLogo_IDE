package backend.parser;

import java.util.ArrayList;
import java.util.List;

import exceptions.SlogoError;

public class ForwardBracketNode implements ExpressionNode {
	private List<ExpressionNode> myChildren;
	private boolean hasBeenExecuted;
	private double result;
	
	public ForwardBracketNode() {
		myChildren = new ArrayList<ExpressionNode>();
		hasBeenExecuted = false;
		result = 0;
	}

	@Override
	public double execute() throws SlogoError {
		if (hasBeenExecuted) {
			return result;
		} else {
			LogoExpressionTreeBuilder myTreeBuilder = new LogoExpressionTreeBuilder();
			result = myTreeBuilder.executeExpressions(myChildren);
			hasBeenExecuted = true;
			return result;			
		}
	}

	@Override
	public int currentNumChildren() {
		return myChildren.size();
	}

	@Override
	public Command getMyCommandType() {
		return Command.ListStart;
	}

	@Override
	public void addChild(ExpressionNode n) {
		myChildren.add(n);
	}

	@Override
	public String getMyName() {
		return "List";
	}

	@Override
	public List<ExpressionNode> getMyChildren() {
		return myChildren;
	}

}