package backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import exceptions.SlogoError;

public class VariableNode implements ExpressionNode {
	private Variable myVariable;
	private boolean setVariable;
	private List<ExpressionNode> myChildren;
	private String key;
	
	public VariableNode(Variable myVariable, String key) {
		this.myVariable = myVariable;
		this.setVariable = false;
		this.key = key;
		myChildren = new ArrayList<ExpressionNode>();
	}

	public void makeMyVariable() {
		setVariable = true;
	}
	
	public Variable getMyVariable() {
		return myVariable;
	}
	
	@Override
	public double execute() throws SlogoError {
		if (setVariable != true && myVariable.getVariableValue() == null) {
			//throw error
		} else {
			double result = 0;
			result =  myChildren.get(0).execute();
			myVariable.setVariableValue(String.valueOf(result));			
			return result;
		}
		return 0;
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
