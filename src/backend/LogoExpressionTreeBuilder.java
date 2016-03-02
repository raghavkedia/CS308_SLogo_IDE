package backend;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import exceptions.SlogoError;

public class LogoExpressionTreeBuilder implements ExpressionTreeBuilder {

	public LogoExpressionTreeBuilder() {
	}
	
	public double executeExpressions(Collection<ExpressionNode> myNodes) throws SlogoError {
		List<ExpressionNode> myNodeCopies = new ArrayList<ExpressionNode>(myNodes);
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = myNodeCopies.get(0);
		List<ExpressionNode> toExecute = new ArrayList<ExpressionNode>();
		double result = 0;
		while (!myNodeCopies.isEmpty()) {
			myNodeCopies.remove(curr);
			if (isSatisfied(curr)) {
				if (myStack.isEmpty()) {
					if (!toExecute.contains(curr)) {
						toExecute.add(curr);
					}
					if(!myNodeCopies.isEmpty()) {
						curr = myNodeCopies.get(0);
					}
				}
				else {
					ExpressionNode parent = myStack.pop();
					parent.addChild(curr);
					curr = parent;
				}
			}
			else {
				myStack.push(curr);
				curr = myNodeCopies.get(0);
			}
		}
		if (!toExecute.contains(curr)) {
			toExecute.add(curr);
		}
		for (ExpressionNode node : toExecute) {
			
			result = node.execute();
//			try {
//				result = node.execute();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				throw new Exception(e.getMessage());
//			}
		}
		return result;
	}

	private boolean isSatisfied(ExpressionNode node) {
		return node.currentNumChildren() >= node.getMyCommandType().numArgs();
	}
}