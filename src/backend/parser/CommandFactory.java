// This entire file is part of my masterpiece.
// Raghav Kedia

//For my masterpiece I implemented reflection in our CommandFactory. This class contains two
//new methods, generateResultReflection and executeMathReflection. For now I only implemented reflection
//for the math operations just for demonstration purposes, but it would not be difficult to do so for all 
//the commands. With reflection this solves our issue of violating the open/close principle. 

package backend.parser;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import backend.Commands.MathOperation;
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
import exceptions.InvalidCommandError;
import exceptions.InvalidIndexColorError;
import exceptions.InvalidIndexShapeError;
import exceptions.InvalidParameterError;
import exceptions.InvalidParametersError;
import exceptions.InvalidQuotientError;
import exceptions.SlogoError;
import exceptions.TooFewParametersError;
import javafx.scene.image.Image;
import util.MathUtil;
import java.lang.reflect.*;

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
	
	//THIS IS PART OF MY MASTERPIECE
	private double executeMathReflection(String operationName, List<Double> myResults) throws SlogoError{
		double result = myResults.get(0);
		myResults.remove(0);
		Object[] arguments = new Object[2];
		Class mathOperation = MathOperation.class;
		try{
			Constructor constructor = mathOperation.getConstructor();
			MathOperation myOperation = (MathOperation) constructor.newInstance();
			Method operate = mathOperation.getMethod(operationName, new Class[] {Double.TYPE, Double.TYPE});
			
			for(Double d : myResults){
				arguments[0] = result;
				arguments[1] = d;
				result = (double) operate.invoke(myOperation, arguments);
			}
		}catch(Exception e){
			throw new InvalidCommandError(myErrorResources.getString("InvalidCommand"));
		}
		
		return result;
	}
	
	//THIS IS PART OF MY MASTERPIECE
	public double generateResultReflection(Command type, String myName, List<ExpressionNode> myChildren, List<ExpressionNode> parameters) throws SlogoError{
		
		//according to the command type it should call the corresponding execute method. For now
		//we will assume that it knows to call executeMathReflection
		
		return executeMathReflection(myName, convertAllNodesToDoubles(myChildren));
		
	}
	
	private List<Double> convertAllNodesToDoubles(List<ExpressionNode> myNodes) throws SlogoError {
		List<Double> executed = new ArrayList<Double>();
		for (ExpressionNode n : myNodes) {
			executed.add(n.execute());
		}
		return executed;
	}

}