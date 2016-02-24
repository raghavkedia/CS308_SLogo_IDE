package backend;

import backend.Command;
import backend.ExpressionNode;

public class BinaryCommandNode implements ExpressionNode {
	Command type;
	ExpressionNode leftChild;
	ExpressionNode rightChild;
	
	public BinaryCommandNode(Command type) {
		this.type = type;
	}
	
	@Override
	public double execute() {
		double leftValue = leftChild.execute();
		double rightValue = rightChild.execute();
		switch(type) {
			case SUM:
				return leftValue + rightValue;
			case DIFFERENCE:
				return leftValue - rightValue;
			case PRODUCT:
				return leftValue * rightValue;
			case QUOTIENT:
				return leftValue / rightValue;
			case REMAINDER:
				return leftValue % rightValue;
			case POW:
				return Math.pow(leftValue, rightValue);
			case LESS:
				return leftValue < rightValue ? 1 : 0;
			case GREATER:
				return leftValue > rightValue ? 1 : 0;
			case EQUAL:
				return leftValue == rightValue ? 1 : 0;
			case NOTEQUAL:
				return leftValue != rightValue ? 1 : 0;
			case AND:
				return ((leftValue != 0) && (rightValue != 0)) ? 1 : 0;
			case OR:
				return ((leftValue != 0) || (rightValue != 0)) ? 1 : 0;
			default:
				break;
		}
		return 0;
	}

}
