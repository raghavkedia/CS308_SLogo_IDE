package backend;

import java.util.List;
import java.util.ResourceBundle;

import exceptions.InvalidQuotientError;
import exceptions.SlogoError;

public class CommandFactory {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/languages/";
	private CharactersList myCharacters;
	private VariablesList myVariablesList;
	private UserDefinedCommands myUserDefinedCommands; 
	private ResourceBundle myErrorResources;
	private double maxWidth = 500;
	private double maxHeight = 500;
	
	public CommandFactory(CharactersList myCharacters, VariablesList myVariablesList, UserDefinedCommands myUserDefinedCommands) {
		this.myCharacters = myCharacters;
		this.myVariablesList = myVariablesList;
		
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
	}
	
	public CommandFactory() {
		
	}

	private void translateCoor(double [] transCoords, Character myCharacter) {
		double translateX = transCoords[0] * Math.cos(convertDegrees(myCharacter.getMyAngle())) 
				+ transCoords[1] * Math.sin(convertDegrees(myCharacter.getMyAngle()));
		double translateY = transCoords[1] * Math.sin(convertDegrees(myCharacter.getMyAngle()));
		
		double [] rawCoords = {translateX, translateY};
		double [] reducedCoords = reduceCoor(rawCoords);
		
		myCharacter.setCurrCoord((int) Math.floor(myCharacter.getCoordX() + reducedCoords[0]),
				(int) Math.floor(myCharacter.getCoordY() + reducedCoords[1]));
	}
	
	private double[] reduceCoor(double [] coor){
	
		double currX = coor[0];
		double currY = coor[1];
		double [] newCoor = new double[2];
		
		if(Math.abs(currX) <= maxWidth && Math.abs(currY) <= maxHeight){
			return coor;
		}
		
		if(Math.abs(currX) >= maxWidth){
			newCoor[0] = maxWidth * (currX / Math.abs(currX));
			newCoor[1] = (currY / Math.abs(currY)) * currY * (maxWidth / currX);
		}
		if(Math.abs(currY) >= maxHeight){
			newCoor[1] = maxHeight * (currY / Math.abs(currY));
			newCoor[0] = (currX / Math.abs(currX)) * currX * (maxHeight / currY);
		}
		
		return newCoor;
		
	}

	private double convertDegrees(double angle) {
		return Math.PI * angle / 180;
	}
	
	private double findDistance(double x1, double x2, double y1, double y2) {
		double deltaX = x1 - x2; 
		double deltaY = y1 - y2;
		return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
	}
	
	private double findDistanceFromHome() {
		double result = 0;
		for (String key : myCharacters.getActiveCharacters()) {
			result = findDistance(0, myCharacters.getCharacter(key).getCoordX(), 0, myCharacters.getCharacter(key).getCoordY());
			myCharacters.getCharacter(key).setCurrCoord(0, 0);
		}
		return result;
	}
	
	public double generateResult(Command type, List<Double> myResults) throws SlogoError{
		double result = 0;
		double leftValue = 0;
		double rightValue = 0;
		switch(type) {
			case Forward:
				for (String key : myCharacters.getActiveCharacters()) {
					translateCoor(new double[]{0, myResults.get(0)}, myCharacters.getCharacter(key));	
				}
				myCharacters.hasUpdated();
				return myResults.get(0);
			case Back:
				for (String key : myCharacters.getActiveCharacters()) {
					translateCoor(new double[]{0, -1 * myResults.get(0)}, myCharacters.getCharacter(key));
				}
				myCharacters.hasUpdated();
				return myResults.get(0);
			case Left:
				for (String key : myCharacters.getActiveCharacters()) {
					myCharacters.getCharacter(key).setMyAngle((myCharacters.getCharacter(key).getMyAngle() - myResults.get(0)) % 360);
				}
				myCharacters.hasUpdated();
				return myResults.get(0);
			case Right:
				for (String key : myCharacters.getActiveCharacters()) {
					myCharacters.getCharacter(key).setMyAngle((myCharacters.getCharacter(key).getMyAngle() + myResults.get(0)) % 360);
				}
				myCharacters.hasUpdated();
				return myResults.get(0);
			case SetHeading:
				for (String key : myCharacters.getActiveCharacters()) {
					result = Math.abs((myCharacters.getCharacter(key).getMyAngle() - myResults.get(0)) % 360);
					myCharacters.getCharacter(key).setMyAngle(myResults.get(0));
				}
				myCharacters.hasUpdated();
				return result;
			case SetTowards:
				for (String key : myCharacters.getActiveCharacters()) {
				double newAngle = 
					90 - Math.atan2(myResults.get(1) - myCharacters.getCharacter(key).getCoordY(), myResults.get(0) - myCharacters.getCharacter(key).getCoordX());
				result = Math.abs((newAngle - myCharacters.getCharacter(key).getMyAngle()) % 360);
				myCharacters.getCharacter(key).setMyAngle(newAngle % 360);
				}
				myCharacters.hasUpdated();
				return result;
			case SetPosition:
				for (String key : myCharacters.getActiveCharacters()) {
				result = findDistance(myResults.get(0), myCharacters.getCharacter(key).getCoordX(), myResults.get(1), myCharacters.getCharacter(key).getCoordY());
				myCharacters.getCharacter(key).setCurrCoord((int) Math.round(myResults.get(0)), (int) Math.round(myResults.get(1)));
				}
				myCharacters.hasUpdated();
				return result;
			case PenDown:
				for (String key : myCharacters.getActiveCharacters()) {
					myCharacters.getCharacter(key).setPenState(true);
				}
				myCharacters.hasUpdated();
				return 1;
			case PenUp:
				for (String key : myCharacters.getActiveCharacters()) {
					myCharacters.getCharacter(key).setPenState(false);
				}
				myCharacters.hasUpdated();
				return 0;
			case ShowTurtle:
				for (String key : myCharacters.getActiveCharacters()) {
					myCharacters.getCharacter(key).setVisability(true);
				}
				myCharacters.hasUpdated();
				return 1;
			case HideTurtle:
				for (String key : myCharacters.getActiveCharacters()) {
					myCharacters.getCharacter(key).setVisability(false);
				}
				myCharacters.hasUpdated();
				return 0;
			case Home:
				result = findDistanceFromHome();
				myCharacters.hasUpdated();
				return result;
			case ClearScreen:
				result = findDistanceFromHome();
				myCharacters.hasUpdated();
				//How to do this? for erasing all lines? deleting list?
				break;
			case XCoordinate:
				for (String key : myCharacters.getActiveCharacters()) {
					result = myCharacters.getCharacter(key).getCoordX();
				}
				return result;
			case YCoordinate:
				for (String key : myCharacters.getActiveCharacters()) {
					result = myCharacters.getCharacter(key).getCoordY();
				}
				return result;
			case Heading:
				for (String key : myCharacters.getActiveCharacters()) {
					result = myCharacters.getCharacter(key).getMyAngle();
				}
				return result;
			case IsPenDown:
				for (String key : myCharacters.getActiveCharacters()) {
					result = myCharacters.getCharacter(key).getPenState() ? 1 : 0;
				}
				return result;
			case IsShowing:
				for (String key : myCharacters.getActiveCharacters()) {
					result = myCharacters.getCharacter(key).getVisability() ? 1 : 0;
				}
				return result;
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
					if(d == 0){
						throw new InvalidQuotientError(myErrorResources.getString("QuotientError"));
					}
					result = result / d;
				}
				return result;
			case Remainder:
				result = myResults.get(0);
				myResults.remove(0);
				for (Double d : myResults) {
					result = result % d;
				}
				return result;
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
			case Not:
				return (myResults.get(0) == 0 ? 1 : 0);
			case MakeVariable:
				return myResults.get(myResults.size() - 1);
			case Repeat:
				return myResults.get(myResults.size() - 1);
			case DoTimes:
				return myResults.get(myResults.size() - 1);
			case For:
				return myResults.get(myResults.size() - 1); 
			case If:
				return myResults.get(myResults.size() - 1);
			case IfElse:
				return myResults.get(myResults.size() - 1);
			case MakeUserInstruction:
				return myResults.get(myResults.size() - 1);
			default:
				break;
		}
		return 0;
	}

}
