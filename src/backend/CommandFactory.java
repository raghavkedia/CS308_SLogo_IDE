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
	
	private void translateCoor(double [] transCoords) {
		double translateX = transCoords[0] * Math.cos(convertDegrees(myCharacter.getMyAngle())) 
				+ transCoords[1] * Math.sin(convertDegrees(myCharacter.getMyAngle()));
		double translateY = transCoords[1] * Math.sin(convertDegrees(myCharacter.getMyAngle()));
		myCharacter.setCurrCoord((int) Math.floor(myCharacter.getCoordX() + translateX),
				(int) Math.floor(myCharacter.getCoordY() + translateY));
		myCharacter.hasUpdated();
		}

	private double convertDegrees(double angle) {
		return Math.PI * angle / 180;
	}
	
	private double findDistance(double x1, double x2, double y1, double y2) {
		double deltaX = x1 - x2; 
		double deltaY = y1 - y2;
		return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
	}
	
	public double generateResult(Command type, List<Double> myResults) {
		double result = 0;
		double leftValue = 0;
		double rightValue = 0;
		switch(type) {
		case Forward:
			translateCoor(new double[]{0, myResults.get(0)});
			return myResults.get(0);
		case Back:
			translateCoor(new double[]{0, -1 * myResults.get(0)});
			return myResults.get(0);
		case Left:
			myCharacter.setMyAngle(myCharacter.getMyAngle() - myResults.get(0));
			myCharacter.hasUpdated();
			return myResults.get(0);
		case Right:
			myCharacter.setMyAngle(myCharacter.getMyAngle() + myResults.get(0));
			myCharacter.hasUpdated();
			return myResults.get(0);
		case SetHeading:
			result = Math.abs(myCharacter.getMyAngle() - myResults.get(0));
			myCharacter.setMyAngle(myResults.get(0));
			myCharacter.hasUpdated();
			return result;
		case SetTowards:
			double newAngle = 
				90 - Math.atan2(myResults.get(1) - myCharacter.getCoordY(), myResults.get(0) - myCharacter.getCoordX());
			result = Math.abs(newAngle - myCharacter.getMyAngle());
			myCharacter.setMyAngle(newAngle);
			myCharacter.hasUpdated();
			return result;
		case SetPosition:
			result = findDistance(myResults.get(0), myCharacter.getCoordX(), myResults.get(1), myCharacter.getCoordY());
			myCharacter.setCurrCoord((int) Math.round(myResults.get(0)), (int) Math.round(myResults.get(1)));
			myCharacter.hasUpdated();
			return result;
		case PenDown:
			myCharacter.setPenState(true);
			myCharacter.hasUpdated();
			return 1;
		case PenUp:
			myCharacter.hasUpdated();
			myCharacter.setPenState(false);
			return 0;
		case ShowTurtle:
			myCharacter.setVisability(true);
			myCharacter.hasUpdated();
			return 1;
		case HideTurtle:
			myCharacter.setVisability(false);
			myCharacter.hasUpdated();
			return 0;
		case Home:
			result = findDistance(0, myCharacter.getCoordX(), 0, myCharacter.getCoordY());
			myCharacter.setCurrCoord(0, 0);
			myCharacter.hasUpdated();
			return result;
		case ClearScreen:
			//How to do this? for erasing all lines? deleting list?
			break;
		case XCoordinate:
			return myCharacter.getCoordX();
		case YCoordinate:
			return myCharacter.getCoordY();
		case Heading:
			return myCharacter.getMyAngle();
		case IsPenDown:
			return myCharacter.getPenState() ? 1 : 0;
		case IsShowing:
			return myCharacter.getVisability() ? 1 : 0;
		case Sum:
			for (Double d : myResults) {
				result += d;
			}
			return result;
		case Difference:
			result = myResults.get(0);
			myResults.remove(0);
			for (Double d : myResults) {
				result -= d;
			}
			return result;
		case Product:
			result = myResults.get(0);
			myResults.remove(0);
			for (Double d : myResults) {
				result = result * d;
			}
			return result;
		case Quotient:
			result = myResults.get(0);
			myResults.remove(0);
			for (Double d : myResults) {
				result = result / d;
			}
			return result;
		case Remainder:
			result = myResults.get(0);
			myResults.remove(0);
			for (Double d : myResults) {
				result = result % d;
			}
		case Minus:
			return -1 * myResults.get(0);
		case Random:
			return Math.floor(Math.random() * myResults.get(0));
		case Sine:
			return Math.sin(convertDegrees(myResults.get(0)));
		case Cosine:
			return Math.cos(convertDegrees(myResults.get(0)));
		case Tangent:
			return Math.tan(convertDegrees(myResults.get(0)));
		case ArcTangent:
			//may throw exception here
			return Math.atan(convertDegrees(myResults.get(0)));
		case NaturalLog:
			//may also throw exception here
			return Math.log(myResults.get(0));
		case Power:
			double base = myResults.get(0); 
			double exp = myResults.get(1);
			return Math.pow(base, exp);
		case Pi:
			return Math.PI;
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
