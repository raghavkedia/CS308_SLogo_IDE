package backend;

import java.util.ArrayList;
import java.util.Collection;

public class VariableNode implements ExpressionNode {
	private Variable myVariable;
	private Collection<ExpressionNode> myChildren;
	
	public VariableNode(Variable myVariable) {
		this.myVariable = myVariable;
		myChildren = new ArrayList<ExpressionNode>();
	}

	@Override
	public double execute() throws Exception {
		double result = 0;
		for (ExpressionNode node : myChildren) {
			result =  node.execute();
			myVariable.setVariableValue(String.valueOf(result));
		}
		return result;
	}

	@Override
	public int currentNumChildren() {
		return myChildren.size();
	}

	@Override
	public Command getMyCommandType() {
		return Command.Variable;
	}

	@Override
	public void addChild(ExpressionNode n) {
		myChildren.add(n);
		//throws exception if greater than 1
	}

}
