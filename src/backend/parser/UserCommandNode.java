package backend.parser;

import java.util.ArrayList;
import java.util.List;

import backend.data.Variable;
import exceptions.SlogoError;

public class UserCommandNode implements ExpressionNode {
	private List<ExpressionNode> myParameters;
	private ExpressionNode myTree;
	List<ExpressionNode> myChildren;
	String myName;
	
	public UserCommandNode(String myName) {
		myParameters = new ArrayList<ExpressionNode>();
		myChildren = new ArrayList<ExpressionNode>();
		this.myName = myName;
	}

	@Override
	public double execute() throws SlogoError {
		return myTree.execute();
	}

	@Override
	public int currentNumChildren() {
		
		return 0;
	}

	@Override
	public Command getMyCommandType() {
		return Command.UserCommand;
	}

	@Override
	public String getMyName() {
		return myName;
	}

	@Override
	public List<ExpressionNode> getMyChildren() {
		return null;
	}

	@Override
	public void addChild(ExpressionNode n) {
		myChildren.add(n);
	}
	
	public void addParameter(ExpressionNode n) {
		if (n.getMyCommandType() == Command.Variable) {
			myParameters.add(n);
		}
		else {
			//throw error
		}
	}
	
	public void setMyTree(ExpressionNode n) {
		this.myTree = n;
	}

}
