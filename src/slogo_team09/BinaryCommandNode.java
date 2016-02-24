package slogo_team09;

import slogo_team09.Command;
import slogo_team09.ExpressionNode;

public class BinaryCommandNode implements ExpressionNode {
	Command type;
	ExpressionNode leftChild;
	ExpressionNode rightChild;
	Result result;
	
	public BinaryCommandNode(Command type) {
		this.type = type;
		result = new GeneralResult();
	}
	
	@Override
	public Result execute() {
		double leftValue = leftChild.execute().getMyDouble();
		double rightValue = rightChild.execute().getMyDouble();
		switch(type) {
			case SUM:
				result.setMyDouble(leftValue + rightValue);
				return result;
			case DIFFERENCE:
				result.setMyDouble(leftValue - rightValue);
				return result;
			case PRODUCT:
				result.setMyDouble(leftValue * rightValue);
				return result;
			case QUOTIENT:
				result.setMyDouble(leftValue / rightValue);
				return result;
			case REMAINDER:
				result.setMyDouble(leftValue % rightValue);
				return result;
			case POW:
				result.setMyDouble(Math.pow(leftValue, rightValue));
				return result;
			case LESS:
				result.setMyDouble(leftValue < rightValue ? 1 : 0);
				return result;
			case GREATER:
				result.setMyDouble(leftValue > rightValue ? 1 : 0);
				return result;
			case EQUAL:
				result.setMyDouble(leftValue == rightValue ? 1 : 0);
				return result;
			case NOTEQUAL:
				result.setMyDouble(leftValue != rightValue ? 1 : 0);
				return result;
			case AND:
				result.setMyDouble(((leftValue != 0) && (rightValue != 0)) ? 1 : 0);
				return result;
			case OR:
				result.setMyDouble(((leftValue != 0) || (rightValue != 0)) ? 1 : 0);
				return result;
			default:
				break;
		}
		return result;
	}

}
