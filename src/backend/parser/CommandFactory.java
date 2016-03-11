
package backend.parser;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import backend.data.Character;
import backend.data.CharactersList;
import backend.data.ColorMap;
import backend.data.Data;
import backend.data.Properties;
import backend.data.ShapeMap;
import backend.data.UserDefinedCommands;
import backend.data.Variable;
import backend.data.VariablesList;
import exceptions.InvalidIndexColorError;
import exceptions.InvalidIndexShapeError;
import exceptions.InvalidParameterError;
import exceptions.InvalidParametersError;
import exceptions.InvalidQuotientError;
import exceptions.SlogoError;
import exceptions.TooFewParametersError;
import javafx.scene.image.Image;
import util.MathUtil;

public class CommandFactory {
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private CharactersList myCharacters;
	private VariablesList myVariablesList;
	private UserDefinedCommands myUserDefinedCommands; 
	private Properties myProperties;
	private ColorMap myColorMap;
	private ShapeMap myShapeMap;
	private ResourceBundle myErrorResources;
	private double maxWidth = 500;
	private double maxHeight = 500;

	public CommandFactory(CharactersList myCharacters, VariablesList myVariablesList, 
			UserDefinedCommands myUserDefinedCommands, Properties myProperties, ColorMap myColorMap,
			ShapeMap myShapeMap) {
		
		this.myCharacters = myCharacters;
		this.myVariablesList = myVariablesList;
		this.myUserDefinedCommands = myUserDefinedCommands;
		this.myProperties = myProperties;
		this.myColorMap = myColorMap;
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
		
	}

	public CommandFactory() {	

	}

	private void translateCoor(double [] transCoords, Character myCharacter) {
		double translateX = transCoords[0] * Math.cos(MathUtil.convertDegrees(myCharacter.getMyAngle())) 
				+ transCoords[1] * Math.sin(MathUtil.convertDegrees(myCharacter.getMyAngle()));
		double translateY = transCoords[1] * Math.cos(MathUtil.convertDegrees(myCharacter.getMyAngle()));

		double [] rawCoords = {translateX, translateY};
		double [] reducedCoords = reduceCoor(rawCoords);

		myCharacter.setCurrCoord(myCharacter.getCoordX() + rawCoords[0],
			myCharacter.getCoordY() + rawCoords[1]);

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

	private double findDistanceFromHome(String key) {
		double result = MathUtil.findDistance(0, myCharacters.getCharacter(key).getCoordX(), 0, myCharacters.getCharacter(key).getCoordY());
		myCharacters.getCharacter(key).setCurrCoord(0, 0);
		return result;
	}

	private double findDistanceFromHome() {
		double result = 0;
		for (String key : myCharacters.getActiveCharacters()) {
			result = MathUtil.findDistance(0, myCharacters.getCharacter(key).getCoordX(), 0, myCharacters.getCharacter(key).getCoordY());
			myCharacters.getCharacter(key).setCurrCoord(0, 0);
		}
		return result;
	}
	private double setBackground(int index) throws InvalidIndexColorError{
		if(!myColorMap.indexExists(index)){
			throw new InvalidIndexColorError(myErrorResources.getString("InvalidIndexColorError"));
		}
		String color = myColorMap.getColor(index);
		myProperties.setBackgroundColor(color);
		return (double) index;
	}
	private double SetPalette(int index, double r, double g, double b){
		myColorMap.addColor(index, r, g, b);
		return index;
	}
	interface TurtleOperation {
		double operationBinary(String key, double value, double b) throws SlogoError;
	}

	private double operate(TurtleOperation turtleOperation, String key, double a, double b) throws SlogoError{
		return turtleOperation.operationBinary(key, a, b);
	}

	private TurtleOperation Forward = (String key, double a, double b) -> {
		translateCoor(new double[]{0, a}, myCharacters.getCharacter(key));
		return a;
	};
	private TurtleOperation Back = (String key, double a, double b) -> {
		translateCoor(new double[]{0, -1 * a}, myCharacters.getCharacter(key));
		return a;
	};
	private TurtleOperation Left = (String key, double a, double b) -> {
		myCharacters.getCharacter(key).setMyAngle((myCharacters.getCharacter(key).getMyAngle() - a) % 360);
		return a;
	};
	private TurtleOperation Right = (String key, double a, double b) -> {
		myCharacters.getCharacter(key).setMyAngle((myCharacters.getCharacter(key).getMyAngle() + a) % 360);
		return a;
	};
	private TurtleOperation SetHeading = (String key, double a, double b) -> {
		double result = Math.abs((myCharacters.getCharacter(key).getMyAngle() - a) % 360);
		myCharacters.getCharacter(key).setMyAngle(a);
		return result;
	};
	private TurtleOperation SetTowards = (String key, double a, double b) -> {
		double newAngle = 
				90 - Math.atan2(b - myCharacters.getCharacter(key).getCoordY(), a - myCharacters.getCharacter(key).getCoordX());
		double result = Math.abs((newAngle - myCharacters.getCharacter(key).getMyAngle()) % 360);
		myCharacters.getCharacter(key).setMyAngle(newAngle % 360);
		return result;
	};
	private TurtleOperation SetPosition = (String key, double a, double b) -> {
		double result = MathUtil.findDistance(a, myCharacters.getCharacter(key).getCoordX(), b, myCharacters.getCharacter(key).getCoordY());
		myCharacters.getCharacter(key).setCurrCoord((int) Math.round(a), (int) Math.round(b));
		return result;
	};
	private TurtleOperation PenDown = (String key, double a, double b) -> {
		myCharacters.getCharacter(key).setPenState(true);
		return 1;
	};
	private TurtleOperation PenUp = (String key, double a, double b) -> {
		myCharacters.getCharacter(key).setPenState(false);
		return 0;
	};
	private TurtleOperation ShowTurtle = (String key, double a, double b) -> {
		myCharacters.getCharacter(key).setVisability(true);
		return 1;
	};
	private TurtleOperation HideTurtle = (String key, double a, double b) -> {
		myCharacters.getCharacter(key).setVisability(false);
		return 0;
	};
	private TurtleOperation Home = (String key, double a, double b) -> findDistanceFromHome(key);
	private TurtleOperation ClearScreen = (String key, double a, double b) -> {
		//set clear screen
		myProperties.setClearScreen(true);
		myCharacters.getCharacter(key).removeLines();
		myProperties.hasUpdated();
		return findDistanceFromHome(key);
	};
	private TurtleOperation XCoordinate = (String key, double a, double b) -> myCharacters.getCharacter(key).getCoordX();
	private TurtleOperation YCoordinate = (String key, double a, double b) -> myCharacters.getCharacter(key).getCoordY();
	private TurtleOperation Heading = (String key, double a, double b) -> myCharacters.getCharacter(key).getMyAngle();
	private TurtleOperation IsPenDown = (String key, double a, double b) -> myCharacters.getCharacter(key).getPenState() ? 1 : 0;;
	private TurtleOperation IsShowing = (String key, double a, double b) -> myCharacters.getCharacter(key).getVisability() ? 1 : 0;
	private TurtleOperation Stamp = (String key, double a, double b) -> {
		myCharacters.getCharacter(key).addStamp(myCharacters.getCharacter(key).getCoordX(), myCharacters.getCharacter(key).getCoordY());
		//needs to return index
		return 0;
	};
	private TurtleOperation ClearStamps = (String key, double a, double b) -> {
		return myCharacters.getCharacter(key).clearStamps();
	};
	private TurtleOperation GetPenColor = (String key, double a, double b) -> {
		return myCharacters.getCharacter(key).getColorIndex();
	};
	private TurtleOperation GetShape = (String key, double a, double b) -> {
		return myCharacters.getCharacter(key).getShapeIndex();
	};
	private TurtleOperation SetPenSize = (String key, double a, double b) -> {
		myCharacters.getCharacter(key).setPenWidth(a);
		return a;
	};
	private TurtleOperation SetPenColor = (String key, double a, double b) -> {
		if(myColorMap.indexExists((int) a)){
			throw new InvalidIndexColorError(myErrorResources.getString("InvalidIndexColorError"));
		}
		myCharacters.getCharacter(key).setColorIndex((int)a);
		String penColor = myColorMap.getColor((int) a);
		myCharacters.getCharacter(key).setPenColor(penColor);
		return a;
	};
	private TurtleOperation SetShape = (String key, double a, double b) -> {
		if(myShapeMap.indexExists((int) a)){
			throw new InvalidIndexShapeError(myErrorResources.getString("InvalidIndexShapeError"));
		}
		myCharacters.getCharacter(key).setShapeIndex((int)a);
		//no need to actually set the image path, frontend can do the lookup
//		String imagePath = myShapeMap.getImagePath((int) a);
//		myCharacters.getCharacter(key).setImagePath(imagePath);
		return a;
	};
	
	private double executeForCharacters(TurtleOperation operation, List<Double> myResults) throws SlogoError{
		double result = 0;
		double a = myResults.size() > 0 ? myResults.get(0) : 0;
		double b = myResults.size() > 1 ? myResults.get(1) : 0;

		for (String key : myCharacters.getActiveCharacters()) {
			result = operate(operation, key, a, b);
		}
		myCharacters.hasUpdated();
		return result;
	}

	interface MultipleParameterOperation {
		double operation(double a, double b);
	}

	private double operate(MultipleParameterOperation mathOperation, double a, double b) {
		return mathOperation.operation(a, b);
	}

	private MultipleParameterOperation Sum = (double a, double b) -> a + b;
	private MultipleParameterOperation Difference = (double a, double b) -> a - b;
	private MultipleParameterOperation Product = (double a, double b) -> a * b;
	private MultipleParameterOperation Quotient = (double a, double b) -> a / b;
	private MultipleParameterOperation Remainder = (double a, double b) -> a % b;
	private MultipleParameterOperation Power = (double a, double b) -> Math.pow(a, b);
	private MultipleParameterOperation LessThan = (double a, double b) -> a < b ? 1 : 0;
	private MultipleParameterOperation GreaterThan = (double a, double b) -> a > b ? 1 : 0;
	private MultipleParameterOperation Equal = (double a, double b) -> a == b ? 1 : 0;
	private MultipleParameterOperation NotEqual = (double a, double b) -> a != b ? 1 : 0;

	private double executeMath(MultipleParameterOperation operation, List<Double> myResults) {
		double result = myResults.get(0);
		myResults.remove(0);
		if (myResults.isEmpty()) {
			//throws not enough arguments
		}
		for (Double d : myResults) {
			result = operate(operation, result, d);
		}
		return result;
	}

	private List<Double> convertAllNodesToDoubles(List<ExpressionNode> myNodes) throws SlogoError {
		List<Double> executed = new ArrayList<Double>();
		for (ExpressionNode n : myNodes) {
			executed.add(n.execute());
		}
		return executed;
	}

	public double generateResult(Command type, String myName, List<ExpressionNode> myChildren, List<ExpressionNode> parameters) throws SlogoError {
		double result = 0;
		List<String> previousActive = new ArrayList<String>(myCharacters.getActiveCharacters());
		switch(type) {
		case And:
			return executeMath(Product, convertAllNodesToDoubles(myChildren)) != 0 ? 1 : 0;
		case ArcTangent:
			return Math.atan(myChildren.get(0).execute());
		case Back:
			return executeForCharacters(Back, convertAllNodesToDoubles(myChildren));
		case ClearScreen:
			return executeForCharacters(ClearScreen, convertAllNodesToDoubles(myChildren));
		case Constant:
			break;
		case Cosine:
			return Math.cos(MathUtil.convertDegrees(myChildren.get(0).execute()));
		case Difference:
			return executeMath(Difference, convertAllNodesToDoubles(myChildren));
		case DoTimes:
			double limit = myChildren.get(0).getMyChildren().get(1).execute();
			result = 0;
			for (double k = 1; k <= limit; k++) {
				result = executeListOfCommands(myChildren, k);
			}
			return result;
		case Equal:
			return executeMath(Equal, convertAllNodesToDoubles(myChildren));
		case For:		
			double start = myChildren.get(0).getMyChildren().get(1).execute();
			double end = myChildren.get(0).getMyChildren().get(2).execute();
			double increment = myChildren.get(0).getMyChildren().get(3).execute();
			result = 0;
			for (double k = start; k <= end; k += increment) {
				result = executeListOfCommands(myChildren, k);
			}
			return result;
		case Forward:
			return executeForCharacters(Forward, convertAllNodesToDoubles(myChildren));
		case GreaterThan:
			return executeMath(GreaterThan, convertAllNodesToDoubles(myChildren));
		case Heading:
			return executeForCharacters(Heading, convertAllNodesToDoubles(myChildren));
		case HideTurtle:
			return executeForCharacters(HideTurtle, convertAllNodesToDoubles(myChildren));
		case Home:
			return executeForCharacters(Home, convertAllNodesToDoubles(myChildren));
		case If:
			return myChildren.get(0).execute() != 0 ? myChildren.get(1).execute() : 0;
		case IfElse:
			return myChildren.get(0).execute() != 0 ? myChildren.get(1).execute() : myChildren.get(2).execute();
		case IsPenDown:
			return executeForCharacters(IsPenDown, convertAllNodesToDoubles(myChildren));
		case IsShowing:
			return executeForCharacters(IsShowing, convertAllNodesToDoubles(myChildren));
		case Left:
			return executeForCharacters(Left, convertAllNodesToDoubles(myChildren));
		case LessThan:
			return executeMath(LessThan, convertAllNodesToDoubles(myChildren));
		case ListStart:
			break;
		case MakeUserInstruction:
			//maybe check?
			ExpressionNode myUserCommand = myChildren.get(0);
			if (myUserCommand instanceof UserCommandNode) {
				for (int k = 0; k < myChildren.get(1).getMyChildren().size() -1 ; k++) {
					if (myChildren.get(1).getMyChildren().get(k).getMyCommandType() == Command.Variable) {
						((UserCommandNode) myUserCommand).addParameter(myChildren.get(1).getMyChildren().get(k));
					}
					else {
						return 0;
					}
				}
				((UserCommandNode) myUserCommand).setMyTree(myChildren.get(2));
			}
			myUserDefinedCommands.addUserDefinedCommand(myUserCommand.getMyName(), myUserCommand);
			return 1;
		case MakeVariable:
			Variable myVariable = myVariablesList.getVariable(myChildren.get(0).getMyName()) != null ? 
					myVariablesList.getVariable(myChildren.get(0).getMyName()) : new Variable(myChildren.get(0).getMyName(), null);
					result = myChildren.get(1).execute();
					myVariable.setVariableValue(String.valueOf(result));
					myVariablesList.addVariable(myVariable);
					myVariablesList.hasUpdated();
					return result;
		case Minus:
			return -1 * myChildren.get(0).execute();
		case NaturalLog:
			return Math.log(myChildren.get(0).execute());
		case Not:
			return myChildren.get(0).execute() == 0 ? 1 : 0;
		case NotEqual:
			return executeMath(NotEqual, convertAllNodesToDoubles(myChildren));
		case Or:
			return executeMath(Sum, convertAllNodesToDoubles(myChildren)) != 0 ? 1 : 0;
		case PenDown:
			return executeForCharacters(PenDown, convertAllNodesToDoubles(myChildren));
		case PenUp:
			return executeForCharacters(PenUp, convertAllNodesToDoubles(myChildren));
		case Pi:
			return Math.PI;
		case Power:
			return executeMath(Power, convertAllNodesToDoubles(myChildren));
		case Product:
			return executeMath(Product, convertAllNodesToDoubles(myChildren));
		case Quotient:
			return executeMath(Quotient, convertAllNodesToDoubles(myChildren));
		case Random:
			return Math.random() * myChildren.get(0).execute();
		case Remainder:
			return executeMath(Remainder, convertAllNodesToDoubles(myChildren));
		case Repeat:
			double times = myChildren.get(0).execute();
			result = 0;
			int repeatTimes = (int) Math.floor(times);
			for (int k = 0; k < repeatTimes; k++) {
				result = myChildren.get(1).execute();
			}
			return result;
		case Right:
			return executeForCharacters(Right, convertAllNodesToDoubles(myChildren));
		case SetHeading:
			return executeForCharacters(SetHeading, convertAllNodesToDoubles(myChildren));
		case SetPosition:
			return executeForCharacters(SetPosition, convertAllNodesToDoubles(myChildren));
		case SetTowards:
			return executeForCharacters(SetTowards, convertAllNodesToDoubles(myChildren));
		case ShowTurtle:
			return executeForCharacters(ShowTurtle, convertAllNodesToDoubles(myChildren));
		case Sine:
			return Math.sin(MathUtil.convertDegrees(myChildren.get(0).execute()));
		case Sum:
			return executeMath(Sum, convertAllNodesToDoubles(myChildren));
		case Tangent:
			return Math.tan(MathUtil.convertDegrees(myChildren.get(0).execute()));
		case Variable:
			return Double.valueOf(myVariablesList.getVariable(myName).getVariableValue());
		case XCoordinate:
			return executeForCharacters(XCoordinate, convertAllNodesToDoubles(myChildren));
		case YCoordinate:
			return executeForCharacters(YCoordinate, convertAllNodesToDoubles(myChildren));
		case Ask:
			//check again that all children of [ node are constants, make method for this?
			changeActiveCharactersTo(myChildren.get(0).getMyChildren());
			result = myChildren.get(1).execute();
			returnActiveCharactersToPrevious(previousActive);
			return result;
		case AskWith:
			myCharacters.getActiveCharacters().clear();
			List<String> askedCharacters = new ArrayList<String>();
			for (Character c : myCharacters.getCharacters()) {
				myCharacters.addActiveCharcter(c.getName());
				if (myChildren.get(0).execute() == 1) {
					askedCharacters.add(c.getName());
				}
				myCharacters.getActiveCharacters().clear();
			}
			askedCharacters.forEach(c -> myCharacters.addActiveCharcter(c));
			result = myChildren.get(1).execute();
			returnActiveCharactersToPrevious(previousActive);
			return result;
		case ID:
			return Double.parseDouble(myCharacters.getActiveCharacters().get(0));
		case ListEnd:
			break;
		case Tell:
			//check that all children of that [ node are constants
			changeActiveCharactersTo(myChildren.get(0).getMyChildren());
			return Double.valueOf(myCharacters.getActiveCharacters().get(myCharacters.getActiveCharacters().size()-1));
		case Turtles:
			return myCharacters.getNumCharacters();
		case UserCommand:
			if(parameters.size() == myChildren.size()) {
				for (int k = 0; k < myChildren.size(); k++) {
					myVariablesList.addVariable(new 
							Variable(parameters.get(k).getMyName(), String.valueOf(myChildren.get(k).execute())));
				}
				return 0;
			}
		case ClearStamps:
			return executeForCharacters(ClearStamps, convertAllNodesToDoubles(myChildren));
		case GetPenColor:
			return executeForCharacters(GetPenColor, convertAllNodesToDoubles(myChildren));
		case GetShape:
			return executeForCharacters(GetShape, convertAllNodesToDoubles(myChildren));
		case SetBackground:
			double i = myChildren.get(0).getMyChildren().get(1).execute();
			return setBackground((int)i);
		case SetPalette:
			double index = myChildren.get(0).getMyChildren().get(1).execute();
			double r = myChildren.get(0).getMyChildren().get(2).execute();
			double g = myChildren.get(0).getMyChildren().get(3).execute();
			double b = myChildren.get(0).getMyChildren().get(4).execute();
			return SetPalette((int)index, r, g, b);
		case SetPenColor:
			return executeForCharacters(SetPenColor, convertAllNodesToDoubles(myChildren));
		case SetPenSize:
			return executeForCharacters(SetPenSize, convertAllNodesToDoubles(myChildren));
		case SetShape:
			return executeForCharacters(SetShape, convertAllNodesToDoubles(myChildren));
		case Stamp:
			return executeForCharacters(Stamp, convertAllNodesToDoubles(myChildren));
		default:
			break;
		}
		return 0;
	}

	private void changeActiveCharactersTo(List<ExpressionNode> myNodeList) throws SlogoError {
		List<Double> turtleIDs = new ArrayList<Double>(convertAllNodesToDoubles(myNodeList));
		myCharacters.getActiveCharacters().clear();
		turtleIDs.forEach(c -> myCharacters.getActiveCharacters().add(String.valueOf(c)));
	}

	private void returnActiveCharactersToPrevious(List<String> previousActive) {
		myCharacters.getActiveCharacters().clear();
		previousActive.forEach(c -> myCharacters.addActiveCharcter(c));
	}

	private double executeListOfCommands(List<ExpressionNode> myChildren, double k) throws SlogoError {
		retreiveOrMakeVariable(myChildren.get(0).getMyChildren().get(0).getMyName()).setVariableValue(String.valueOf(k));
		myVariablesList.hasUpdated();
		return myChildren.get(1).execute();
	}

	private Variable retreiveOrMakeVariable(String variableName) {
		Variable myVariable = myVariablesList.getVariable(variableName) != null ? myVariablesList.getVariable(variableName) : new Variable(variableName, null);
		myVariablesList.addVariable(myVariable);
		return myVariable;
	}

	public double generateResults(Command type, List<Double> myResults) throws SlogoError{
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
				result = MathUtil.findDistance(myResults.get(0), myCharacters.getCharacter(key).getCoordX(), myResults.get(1), myCharacters.getCharacter(key).getCoordY());
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
			if(myResults.size() < 2){
				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
			}
			for (Double d : myResults) {
				result += d;
			}
			return result;
		case Difference:
			if(myResults.size() < 2){
				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
			}
			result = myResults.get(0);
			myResults.remove(0);
			for (Double d : myResults) {
				result -= d;
			}
			return result;
		case Product:
			if(myResults.size() < 2){
				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
			}
			result = myResults.get(0);
			myResults.remove(0);
			for (Double d : myResults) {
				result = result * d;
			}
			return result;
		case Quotient:
			if(myResults.size() < 2){
				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
			}
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
			if(myResults.size() < 2){
				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
			}
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
			return Math.sin(MathUtil.convertDegrees(myResults.get(0)));
		case Cosine:
			return Math.cos(MathUtil.convertDegrees(myResults.get(0)));
		case Tangent:
			return Math.tan(MathUtil.convertDegrees(myResults.get(0)));
		case ArcTangent:
			//may throw exception here
			return Math.atan(MathUtil.convertDegrees(myResults.get(0)));
		case NaturalLog:
			if(myResults.get(0) == 0){
				throw new InvalidParameterError(myErrorResources.getString("InvalidParameter") + "cannot take natural log of 0!");
			}
			//may also throw exception here
			return Math.log(myResults.get(0));
		case Power:
			if(myResults.size() != 2){
				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
			}
			double base = myResults.get(0); 
			double exp = myResults.get(1);
			return Math.pow(base, exp);
		case Pi:
			return Math.PI;
		case LessThan:
			if(myResults.size() != 2){
				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
			}
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (leftValue < rightValue ? 1 : 0);
		case GreaterThan:
			if(myResults.size() != 2){
				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
			}
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (leftValue > rightValue ? 1 : 0);
		case Equal:
			if(myResults.size() != 2){
				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
			}
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return(leftValue == rightValue ? 1 : 0);
		case NotEqual:
			if(myResults.size() != 2){
				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
			}
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (leftValue != rightValue ? 1 : 0);
		case And:
			if(myResults.size() < 2){
				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
			}
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (((leftValue != 0) && (rightValue != 0)) ? 1 : 0);
		case Or:
			if(myResults.size() < 2){
				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
			}
			leftValue =  myResults.get(0);
			rightValue = myResults.get(1);
			return (((leftValue != 0) || (rightValue != 0)) ? 1 : 0);
		case Not:
			return (myResults.get(0) == 0 ? 1 : 0);
		case MakeVariable:
			return myResults.get(myResults.size() - 1);
		case Repeat:
			double times = myResults.get(0);
			int repeatTimes = (int) Math.floor(times);
			for (int k = 0; k < repeatTimes; k++) {
				result = myResults.get(1);
			}
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