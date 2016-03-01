package backend;

import java.util.ArrayList;
import java.util.Collection;

public class ForwardBracketNode implements ExpressionNode {
	private Collection<ExpressionNode> myChildren;
	private boolean hasBeenExecuted;
	private double result;
	
	public ForwardBracketNode() {
		myChildren = new ArrayList<ExpressionNode>();
		hasBeenExecuted = false;
		result = 0;
	}

	@Override
	public double execute() throws Exception {
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

}