package backend.parser;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
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
import exceptions.InvalidCharacterError;
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

	private void translateCoor(double [] transCoords, Character myCharacter) {
		double translateX = transCoords[0] * Math.cos(MathUtil.convertDegrees(myCharacter.getMyAngle())) 
				+ transCoords[1] * Math.sin(MathUtil.convertDegrees(myCharacter.getMyAngle()));
		double translateY = transCoords[1] * Math.cos(MathUtil.convertDegrees(myCharacter.getMyAngle()));
		myCharacter.setCurrCoord(myCharacter.getCoordX() + translateX,
			myCharacter.getCoordY() + translateY);

	}


	private double findDistanceFromHome(String key) throws InvalidCharacterError{
		double result = MathUtil.findDistance(0, myCharacters.getCharacter(key).getCoordX(), 0, myCharacters.getCharacter(key).getCoordY());
		myCharacters.getCharacter(key).setCurrCoord(0, 0);
		return result;
	}

//	private double findDistanceFromHome() throws InvalidCharacterError{
//		double result = 0;
//		for (String key : myCharacters.getActiveCharacters()) {
//			result = MathUtil.findDistance(0, myCharacters.getCharacter(key).getCoordX(), 0, myCharacters.getCharacter(key).getCoordY());
//			myCharacters.getCharacter(key).setCurrCoord(0, 0);
//		}
//		return result;
//	}
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
		myCharacters.getCharacter(key).removeLines();
		executeForCharacters(SetHeading, new ArrayList<Double>(){{
			add(0.0);
			add(0.0);
		}});
		double result = findDistanceFromHome(key);
		myProperties.setClearScreen(true);
		myProperties.hasUpdated();
		return result;
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
		myCharacters.getCharacter(key).setColorIndex((int) a);
		String penColor = myColorMap.getColor((int) a);
		myCharacters.getCharacter(key).setPenColor(penColor);
		return a;
	};
	private TurtleOperation SetShape = (String key, double a, double b) -> {
		if(myShapeMap.indexExists((int) a)){
			throw new InvalidIndexShapeError(myErrorResources.getString("InvalidIndexShapeError"));
		}
		myCharacters.getCharacter(key).setShapeIndex((int)a);
		String imagePath = myShapeMap.getImagePath((int) a);
		myCharacters.getCharacter(key).setImagePath(imagePath);
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

	interface MultipleParameterOperation{
		double operation(double a, double b) throws SlogoError;
	}

	private double operate(MultipleParameterOperation mathOperation, double a, double b) throws SlogoError{
		return mathOperation.operation(a, b);
	}

	private MultipleParameterOperation Sum = (double a, double b) -> a + b;
	private MultipleParameterOperation Difference = (double a, double b) -> a - b;
	private MultipleParameterOperation Product = (double a, double b) -> a * b;
	private MultipleParameterOperation Quotient = (double a, double b) -> {
		if(b == 0){
			throw new InvalidQuotientError(myErrorResources.getString("QuotientError"));
		}
		return a / b;
	};
	private MultipleParameterOperation Remainder = (double a, double b) -> a % b;
	private MultipleParameterOperation Power = (double a, double b) -> Math.pow(a, b);
	private MultipleParameterOperation LessThan = (double a, double b) -> a < b ? 1 : 0;
	private MultipleParameterOperation GreaterThan = (double a, double b) -> a > b ? 1 : 0;
	private MultipleParameterOperation Equal = (double a, double b) -> a == b ? 1 : 0;
	private MultipleParameterOperation NotEqual = (double a, double b) -> a != b ? 1 : 0;

	private double executeMath(MultipleParameterOperation operation, List<Double> myResults) throws SlogoError{
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
		case AND:
			return executeMath(Product, convertAllNodesToDoubles(myChildren)) != 0 ? 1 : 0;
		case ARCTANGENT:
			return Math.atan(myChildren.get(0).execute());
		case BACK:
			return executeForCharacters(Back, convertAllNodesToDoubles(myChildren));
		case CLEARSCREEN:
			return executeForCharacters(ClearScreen, convertAllNodesToDoubles(myChildren));
		case CONSTANT:
			break;
		case COSINE:
			return Math.cos(MathUtil.convertDegrees(myChildren.get(0).execute()));
		case DIFFERENCE:
			return executeMath(Difference, convertAllNodesToDoubles(myChildren));
		case DOTIMES:
			double limit = myChildren.get(0).getMyChildren().get(1).execute();
			result = 0;
			for (double k = 1; k <= limit; k++) {
				result = executeListOfCommands(myChildren, k);
			}
			return result;
		case EQUAL:
			return executeMath(Equal, convertAllNodesToDoubles(myChildren));
		case FOR:		
			double start = myChildren.get(0).getMyChildren().get(1).execute();
			double end = myChildren.get(0).getMyChildren().get(2).execute();
			double increment = myChildren.get(0).getMyChildren().get(3).execute();
			result = 0;
			for (double k = start; k <= end; k += increment) {
				result = executeListOfCommands(myChildren, k);
			}
			return result;
		case FORWARD:
			return executeForCharacters(Forward, convertAllNodesToDoubles(myChildren));
		case GREATERTHAN:
			return executeMath(GreaterThan, convertAllNodesToDoubles(myChildren));
		case HEADING:
			return executeForCharacters(Heading, convertAllNodesToDoubles(myChildren));
		case HIDETURTLE:
			return executeForCharacters(HideTurtle, convertAllNodesToDoubles(myChildren));
		case HOME:
			return executeForCharacters(Home, convertAllNodesToDoubles(myChildren));
		case IF:
			return myChildren.get(0).execute() != 0 ? myChildren.get(1).execute() : 0;
		case IFELSE:
			return myChildren.get(0).execute() != 0 ? myChildren.get(1).execute() : myChildren.get(2).execute();
		case ISPENDOWN:
			return executeForCharacters(IsPenDown, convertAllNodesToDoubles(myChildren));
		case ISSHOWING:
			return executeForCharacters(IsShowing, convertAllNodesToDoubles(myChildren));
		case LEFT:
			return executeForCharacters(Left, convertAllNodesToDoubles(myChildren));
		case LESSTHAN:
			return executeMath(LessThan, convertAllNodesToDoubles(myChildren));
		case LISTSTART:
			break;
		case MAKEUSERINSTRUCTION:
			//maybe check?
			ExpressionNode myUserCommand = myChildren.get(0);
			if (myUserCommand instanceof UserCommandNode) {
				for (int k = 0; k < myChildren.get(1).getMyChildren().size() -1 ; k++) {
					if (myChildren.get(1).getMyChildren().get(k).getMyCommandType() == Command.VARIABLE) {
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
		case MAKEVARIABLE:
			Variable myVariable = myVariablesList.getVariable(myChildren.get(0).getMyName()) != null ? 
					myVariablesList.getVariable(myChildren.get(0).getMyName()) : new Variable(myChildren.get(0).getMyName(), null);
					result = myChildren.get(1).execute();
					myVariable.setVariableValue(String.valueOf(result));
					myVariablesList.addVariable(myVariable);
					myVariablesList.hasUpdated();
					return result;
		case MINUS:
			return -1 * myChildren.get(0).execute();
		case NATURALLOG:
			return Math.log(myChildren.get(0).execute());
		case NOT:
			return myChildren.get(0).execute() == 0 ? 1 : 0;
		case NOTEQUAL:
			return executeMath(NotEqual, convertAllNodesToDoubles(myChildren));
		case OR:
			return executeMath(Sum, convertAllNodesToDoubles(myChildren)) != 0 ? 1 : 0;
		case PENDOWN:
			return executeForCharacters(PenDown, convertAllNodesToDoubles(myChildren));
		case PENUP:
			return executeForCharacters(PenUp, convertAllNodesToDoubles(myChildren));
		case PI:
			return Math.PI;
		case POWER:
			return executeMath(Power, convertAllNodesToDoubles(myChildren));
		case PRODUCT:
			return executeMath(Product, convertAllNodesToDoubles(myChildren));
		case QUOTIENT:
			return executeMath(Quotient, convertAllNodesToDoubles(myChildren));
		case RANDOM:
			return Math.random() * myChildren.get(0).execute();
		case REMAINDER:
			return executeMath(Remainder, convertAllNodesToDoubles(myChildren));
		case REPEAT:
			double times = myChildren.get(0).execute();
			result = 0;
			int repeatTimes = (int) Math.floor(times);
			for (int k = 0; k < repeatTimes; k++) {
				result = myChildren.get(1).execute();
			}
			return result;
		case RIGHT:
			return executeForCharacters(Right, convertAllNodesToDoubles(myChildren));
		case SETHEADING:
			return executeForCharacters(SetHeading, convertAllNodesToDoubles(myChildren));
		case SETPOSITION:
			return executeForCharacters(SetPosition, convertAllNodesToDoubles(myChildren));
		case SETTOWARDS:
			return executeForCharacters(SetTowards, convertAllNodesToDoubles(myChildren));
		case SHOWTURTLE:
			return executeForCharacters(ShowTurtle, convertAllNodesToDoubles(myChildren));
		case SINE:
			return Math.sin(MathUtil.convertDegrees(myChildren.get(0).execute()));
		case SUM:
			return executeMath(Sum, convertAllNodesToDoubles(myChildren));
		case TANGENT:
			return Math.tan(MathUtil.convertDegrees(myChildren.get(0).execute()));
		case VARIABLE:
			return Double.valueOf(myVariablesList.getVariable(myName).getVariableValue());
		case XCOORDINATE:
			return executeForCharacters(XCoordinate, convertAllNodesToDoubles(myChildren));
		case YCOORDINATE:
			return executeForCharacters(YCoordinate, convertAllNodesToDoubles(myChildren));
		case ASK:
			//check again that all children of [ node are constants, make method for this?
			changeActiveCharactersTo(myChildren.get(0).getMyChildren().subList(0, myChildren.get(0).getMyChildren().size() - 1));
			result = myChildren.get(1).execute();
			returnActiveCharactersToPrevious(previousActive);
			return result;
		case ASKWITH:
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
		case LISTEND:
			break;
		case TELL:
			//check that all children of that [ node are constants
			changeActiveCharactersTo(myChildren.get(0).getMyChildren().subList(0, myChildren.get(0).getMyChildren().size()- 2));
			return Double.valueOf(myCharacters.getActiveCharacters().get(myCharacters.getActiveCharacters().size()-1));
		case TURTLES:
			return myCharacters.getNumCharacters();
		case USERCOMMAND:
			if(parameters.size() == myChildren.size()) {
				for (int k = 0; k < myChildren.size(); k++) {
					myVariablesList.addVariable(new 
							Variable(parameters.get(k).getMyName(), String.valueOf(myChildren.get(k).execute())));
				}
				return 0;
			}
		case CLEARSTAMPS:
			return executeForCharacters(ClearStamps, convertAllNodesToDoubles(myChildren));
		case GETPENCOLOR:
			return executeForCharacters(GetPenColor, convertAllNodesToDoubles(myChildren));
		case GETSHAPE:
			return executeForCharacters(GetShape, convertAllNodesToDoubles(myChildren));
		case SETBACKGROUND:
			double i = myChildren.get(0).getMyChildren().get(1).execute();
			return setBackground((int)i);
		case SETPALETTE:
			double index = myChildren.get(0).getMyChildren().get(1).execute();
			double r = myChildren.get(0).getMyChildren().get(2).execute();
			double g = myChildren.get(0).getMyChildren().get(3).execute();
			double b = myChildren.get(0).getMyChildren().get(4).execute();
			return SetPalette((int)index, r, g, b);
		case SETPENCOLOR:
			return executeForCharacters(SetPenColor, convertAllNodesToDoubles(myChildren));
		case SETPENSIZE:
			return executeForCharacters(SetPenSize, convertAllNodesToDoubles(myChildren));
		case SETSHAPE:
			return executeForCharacters(SetShape, convertAllNodesToDoubles(myChildren));
		case STAMP:
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

//	public double generateResults(Command type, List<Double> myResults) throws SlogoError{
//		double result = 0;
//		double leftValue = 0;
//		double rightValue = 0;
//		switch(type) {
//		case Forward:
//			for (String key : myCharacters.getActiveCharacters()) {
//				translateCoor(new double[]{0, myResults.get(0)}, myCharacters.getCharacter(key));	
//			}
//			myCharacters.hasUpdated();
//			return myResults.get(0);
//		case Back:
//			for (String key : myCharacters.getActiveCharacters()) {
//				translateCoor(new double[]{0, -1 * myResults.get(0)}, myCharacters.getCharacter(key));
//			}
//			myCharacters.hasUpdated();
//			return myResults.get(0);
//		case Left:
//			for (String key : myCharacters.getActiveCharacters()) {
//				myCharacters.getCharacter(key).setMyAngle((myCharacters.getCharacter(key).getMyAngle() - myResults.get(0)) % 360);
//			}
//			myCharacters.hasUpdated();
//			return myResults.get(0);
//		case Right:
//			for (String key : myCharacters.getActiveCharacters()) {
//				myCharacters.getCharacter(key).setMyAngle((myCharacters.getCharacter(key).getMyAngle() + myResults.get(0)) % 360);
//			}
//			myCharacters.hasUpdated();
//			return myResults.get(0);
//		case SetHeading:
//			for (String key : myCharacters.getActiveCharacters()) {
//				result = Math.abs((myCharacters.getCharacter(key).getMyAngle() - myResults.get(0)) % 360);
//				myCharacters.getCharacter(key).setMyAngle(myResults.get(0));
//			}
//			myCharacters.hasUpdated();
//			return result;
//		case SetTowards:
//			for (String key : myCharacters.getActiveCharacters()) {
//				double newAngle = 
//						90 - Math.atan2(myResults.get(1) - myCharacters.getCharacter(key).getCoordY(), myResults.get(0) - myCharacters.getCharacter(key).getCoordX());
//				result = Math.abs((newAngle - myCharacters.getCharacter(key).getMyAngle()) % 360);
//				myCharacters.getCharacter(key).setMyAngle(newAngle % 360);
//			}
//			myCharacters.hasUpdated();
//			return result;
//		case SetPosition:
//			for (String key : myCharacters.getActiveCharacters()) {
//				result = MathUtil.findDistance(myResults.get(0), myCharacters.getCharacter(key).getCoordX(), myResults.get(1), myCharacters.getCharacter(key).getCoordY());
//				myCharacters.getCharacter(key).setCurrCoord((int) Math.round(myResults.get(0)), (int) Math.round(myResults.get(1)));
//			}
//			myCharacters.hasUpdated();
//			return result;
//		case PenDown:
//			for (String key : myCharacters.getActiveCharacters()) {
//				myCharacters.getCharacter(key).setPenState(true);
//			}
//			myCharacters.hasUpdated();
//			return 1;
//		case PenUp:
//			for (String key : myCharacters.getActiveCharacters()) {
//				myCharacters.getCharacter(key).setPenState(false);
//			}
//			myCharacters.hasUpdated();
//			return 0;
//		case ShowTurtle:
//			for (String key : myCharacters.getActiveCharacters()) {
//				myCharacters.getCharacter(key).setVisability(true);
//			}
//			myCharacters.hasUpdated();
//			return 1;
//		case HideTurtle:
//			for (String key : myCharacters.getActiveCharacters()) {
//				myCharacters.getCharacter(key).setVisability(false);
//			}
//			myCharacters.hasUpdated();
//			return 0;
//		case Home:
//			result = findDistanceFromHome();
//			myCharacters.hasUpdated();
//			return result;
//		case ClearScreen:
//			result = findDistanceFromHome();
//			myCharacters.hasUpdated();
//			//How to do this? for erasing all lines? deleting list?
//			break;
//		case XCoordinate:
//			for (String key : myCharacters.getActiveCharacters()) {
//				result = myCharacters.getCharacter(key).getCoordX();
//			}
//			return result;
//		case YCoordinate:
//			for (String key : myCharacters.getActiveCharacters()) {
//				result = myCharacters.getCharacter(key).getCoordY();
//			}
//			return result;
//		case Heading:
//			for (String key : myCharacters.getActiveCharacters()) {
//				result = myCharacters.getCharacter(key).getMyAngle();
//			}
//			return result;
//		case IsPenDown:
//			for (String key : myCharacters.getActiveCharacters()) {
//				result = myCharacters.getCharacter(key).getPenState() ? 1 : 0;
//			}
//			return result;
//		case IsShowing:
//			for (String key : myCharacters.getActiveCharacters()) {
//				result = myCharacters.getCharacter(key).getVisability() ? 1 : 0;
//			}
//			return result;
//		case Sum:
//			if(myResults.size() < 2){
//				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
//			}
//			for (Double d : myResults) {
//				result += d;
//			}
//			return result;
//		case Difference:
//			if(myResults.size() < 2){
//				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
//			}
//			result = myResults.get(0);
//			myResults.remove(0);
//			for (Double d : myResults) {
//				result -= d;
//			}
//			return result;
//		case Product:
//			if(myResults.size() < 2){
//				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
//			}
//			result = myResults.get(0);
//			myResults.remove(0);
//			for (Double d : myResults) {
//				result = result * d;
//			}
//			return result;
//		case Quotient:
//			if(myResults.size() < 2){
//				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
//			}
//			result = myResults.get(0);
//			myResults.remove(0);
//			for (Double d : myResults) {
//				if(d == 0){
//					throw new InvalidQuotientError(myErrorResources.getString("QuotientError"));
//				}
//				result = result / d;
//			}
//			return result;
//		case Remainder:
//			if(myResults.size() < 2){
//				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
//			}
//			result = myResults.get(0);
//			myResults.remove(0);
//			for (Double d : myResults) {
//				result = result % d;
//			}
//			return result;
//		case Minus:
//			return -1 * myResults.get(0);
//		case Random:
//			return Math.floor(Math.random() * myResults.get(0));
//		case Sine:
//			return Math.sin(MathUtil.convertDegrees(myResults.get(0)));
//		case Cosine:
//			return Math.cos(MathUtil.convertDegrees(myResults.get(0)));
//		case Tangent:
//			return Math.tan(MathUtil.convertDegrees(myResults.get(0)));
//		case ArcTangent:
//			//may throw exception here
//			return Math.atan(MathUtil.convertDegrees(myResults.get(0)));
//		case NaturalLog:
//			if(myResults.get(0) == 0){
//				throw new InvalidParameterError(myErrorResources.getString("InvalidParameter") + "cannot take natural log of 0!");
//			}
//			//may also throw exception here
//			return Math.log(myResults.get(0));
//		case Power:
//			if(myResults.size() != 2){
//				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
//			}
//			double base = myResults.get(0); 
//			double exp = myResults.get(1);
//			return Math.pow(base, exp);
//		case Pi:
//			return Math.PI;
//		case LessThan:
//			if(myResults.size() != 2){
//				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
//			}
//			leftValue =  myResults.get(0);
//			rightValue = myResults.get(1);
//			return (leftValue < rightValue ? 1 : 0);
//		case GreaterThan:
//			if(myResults.size() != 2){
//				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
//			}
//			leftValue =  myResults.get(0);
//			rightValue = myResults.get(1);
//			return (leftValue > rightValue ? 1 : 0);
//		case Equal:
//			if(myResults.size() != 2){
//				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
//			}
//			leftValue =  myResults.get(0);
//			rightValue = myResults.get(1);
//			return(leftValue == rightValue ? 1 : 0);
//		case NotEqual:
//			if(myResults.size() != 2){
//				throw new InvalidParametersError(myErrorResources.getString("WrongNumberOfParameters"));
//			}
//			leftValue =  myResults.get(0);
//			rightValue = myResults.get(1);
//			return (leftValue != rightValue ? 1 : 0);
//		case And:
//			if(myResults.size() < 2){
//				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
//			}
//			leftValue =  myResults.get(0);
//			rightValue = myResults.get(1);
//			return (((leftValue != 0) && (rightValue != 0)) ? 1 : 0);
//		case Or:
//			if(myResults.size() < 2){
//				throw new TooFewParametersError(myErrorResources.getString("TooFewParameters"));
//			}
//			leftValue =  myResults.get(0);
//			rightValue = myResults.get(1);
//			return (((leftValue != 0) || (rightValue != 0)) ? 1 : 0);
//		case Not:
//			return (myResults.get(0) == 0 ? 1 : 0);
//		case MakeVariable:
//			return myResults.get(myResults.size() - 1);
//		case Repeat:
//			double times = myResults.get(0);
//			int repeatTimes = (int) Math.floor(times);
//			for (int k = 0; k < repeatTimes; k++) {
//				result = myResults.get(1);
//			}
//			return myResults.get(myResults.size() - 1);
//		case DoTimes:
//			return myResults.get(myResults.size() - 1);
//		case For:
//			return myResults.get(myResults.size() - 1); 
//		case If:
//			return myResults.get(myResults.size() - 1);
//		case IfElse:
//			return myResults.get(myResults.size() - 1);
//		case MakeUserInstruction:
//			return myResults.get(myResults.size() - 1);
//		default:
//			break;
//		}
//		return 0;
//	}

}