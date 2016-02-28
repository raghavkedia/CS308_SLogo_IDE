package backend;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LogoExpressionTreeBuilder implements ExpressionTreeBuilder {

	public LogoExpressionTreeBuilder() {
	}
	
	public double executeExpressions(List<ExpressionNode> myNodes) {
		List<ExpressionNode> myNodeCopies = new ArrayList<ExpressionNode>(myNodes);
		Stack<ExpressionNode> myStack = new Stack<ExpressionNode>();
		ExpressionNode curr = myNodeCopies.get(0);
		double result = 0;
		while (!myNodeCopies.isEmpty()) {
			if (isSatisfied(curr)) {
				myNodeCopies.remove(curr);
				if (myStack.isEmpty()) {
					//throws an exception for not enough arguments
				}
				else {
					ExpressionNode parent = myStack.pop();
					parent.addChild(curr);
					if (myStack.isEmpty()) {
						result = parent.execute();
					}
					else {
						curr = parent;
					}
				}
			}
			else {
				myStack.push(curr);
				curr = myNodeCopies.get(0);
			}
		}
		return result;
	}
	
	private boolean isSatisfied(ExpressionNode node) {
		return node.currentNumChildren() == node.getMyCommandType().numArgs();
	}
}