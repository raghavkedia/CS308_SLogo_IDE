package backend.parser;

import java.util.ArrayList;
import java.util.List;

import backend.data.Variable;
import exceptions.SlogoError;

public class UserCommandNode implements ExpressionNode {
	private List<ExpressionNode> myParameters;
	private ExpressionNode myTree;
	List<ExpressionNode> myChildren;
	CommandFactory myFactory;
	boolean active;
	String myName;
	
	public UserCommandNode(String myName, CommandFactory  myFactory) {
		myParameters = new ArrayList<ExpressionNode>();
		myChildren = new ArrayList<ExpressionNode>();
		this.myName = myName;
		this.myFactory = myFactory;
		this.active = false;
	}

	@Override
	public double execute() throws SlogoError {
		double result = myFactory.generateResult(getMyCommandType(), myName, myChildren, myParameters);
//		double result = myTree.execute();
//		myChildren.clear();
		return result;
	}

	@Override
	public int currentNumChildren() {
		if (active) {
			return this.myChildren.size() - myParameters.size();
		}
		return 0;
	}

	@Override
	public Command getMyCommandType() {
		return Command.USERCOMMAND;
	}

	@Override
	public String getMyName() {
		return myName;
	}

	@Override
	public List<ExpressionNode> getMyChildren() {
		return myChildren;
	}

	@Override
	public void addChild(ExpressionNode n) {
		myChildren.add(n);
	}
	
	public void addParameter(ExpressionNode n) {
		if (n.getMyCommandType() == Command.VARIABLE) {
			myParameters.add(n);
		}
		else {
			//throw error
		}
	}
	
	public List<ExpressionNode> getParameters() {
		return myParameters;
	}
	
	public void setMyTree(ExpressionNode n) {
		this.myTree = n;
	}
	
	public void activateCommand() {
		this.active = true;
	}

}
