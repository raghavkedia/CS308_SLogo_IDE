package backend;

import java.util.ArrayList;
import java.util.Collection;

public class ForwardBracketNode implements ExpressionNode {
	private Collection<ExpressionNode> myChildren;
	
	public ForwardBracketNode() {
		myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public double execute() {
		LogoExpressionTreeBuilder myTreeBuilder = new LogoExpressionTreeBuilder();
		double result = 0;
		result = myTreeBuilder.executeExpressions(myChildren);
		return result;
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
