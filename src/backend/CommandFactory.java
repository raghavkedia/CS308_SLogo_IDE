package backend;

import java.util.List;

public class CommandFactory {
	enum FactoryType{
		NORMAL, ALL;
	}
	private FactoryType myType;
	private Character myCharacter;
	
	public CommandFactory() {
		myType = FactoryType.NORMAL;
	}
	
	public void setCharacter(Character myCharacter) {
		this.myCharacter = myCharacter;
	}
	public void setType(FactoryType myType) {
		this.myType = myType;
	}
	
	public double generateResult(Command type, List<Double> myResults) {
		double result = 0;
		double leftValue = 0;
		double rightValue = 0;
		switch(type) {
		case Forward:
			break;
		case Back:
			break;
		case Left:
			break;
		case Right:
			break;
		case SetHeading:
			break;
		case Sum:
			for (Double d : myResults) {
				result += d;
			}
			return result;
		case Difference:
			result = myResults.get(0) * 2;
			for (Double d : myResults) {
				result -= d;
			}
			return result;
		case Product:
			result = 1;
			for (Double d : myResults) {
				result = result * d;
			}
			return result;
		case Quotient:
			result = myResults.get(0) * myResults.get(0);
			for (Double d : myResults) {
				result = result / d;
			}
			return result;
		case Remainder:
			result = myResults.get(0) + myResults.get(0);
			for (Double d : myResults) {
				result = result % d;
			}
		case Power:
			double base = myResults.get(0); 
			double exp = myResults.get(1);
			return Math.pow(base, exp);
		case LessThan:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (leftValue < rightValue ? 1 : 0);
		case GreaterThan:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (leftValue > rightValue ? 1 : 0);
		case Equal:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return(leftValue == rightValue ? 1 : 0);
		case NotEqual:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (leftValue != rightValue ? 1 : 0);
		case And:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (((leftValue != 0) && (rightValue != 0)) ? 1 : 0);
		case Or:
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (((leftValue != 0) || (rightValue != 0)) ? 1 : 0);
		default:
			break;
	}
		return 0;
	}
}
