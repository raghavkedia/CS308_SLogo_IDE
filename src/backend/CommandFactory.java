package backend;

import java.util.List;

public class CommandFactory {
	enum FactoryType{
		NORMAL, ALL;
	}
	private FactoryType myType;
	
	public CommandFactory() {
		myType = FactoryType.NORMAL;
	}
	
	public void setType(FactoryType myType) {
		this.myType = myType;
	}
	
	public double generateResult(Command type, List<Double> myResults) {
		double result = 0;
		double leftValue = 0;
		double rightValue = 0;
		switch(type) {
		case SUM:
			for (Double d : myResults) {
				result += d;
			}
			return result;
		case DIFFERENCE:
			result = myResults.get(0) * 2;
			for (Double d : myResults) {
				result -= d;
			}
			return result;
		case PRODUCT:
			result = 1;
			for (Double d : myResults) {
				result = result * d;
			}
			return result;
		case QUOTIENT:
			result = myResults.get(0) * myResults.get(0);
			for (Double d : myResults) {
				result = result / d;
			}
			return result;
		case REMAINDER:
			result = myResults.get(0) + myResults.get(0);
			for (Double d : myResults) {
				result = result % d;
			}
		case POW:
			double base = myResults.get(0); 
			double exp = myResults.get(1);
			return Math.pow(base, exp);
		case LESS:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (leftValue < rightValue ? 1 : 0);
		case GREATER:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (leftValue > rightValue ? 1 : 0);
		case EQUAL:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return(leftValue == rightValue ? 1 : 0);
		case NOTEQUAL:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (leftValue != rightValue ? 1 : 0);
		case AND:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (((leftValue != 0) && (rightValue != 0)) ? 1 : 0);
		case OR:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (((leftValue != 0) || (rightValue != 0)) ? 1 : 0);
		default:
			break;
	}
		return 0;
	}
}
