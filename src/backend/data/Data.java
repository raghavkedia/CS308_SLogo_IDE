package backend.data;

import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

public class Data implements Querryable{
	
	public enum PenPattern{
		DASHED, DOTTED, SOLID;
	}
	
	private CommandHistory myCommandHistory;
	private VariablesList myVariablesList;
	private CharactersList myCharactersList;
	private UserDefinedCommands myUserDefinedCommands;
	private Color myBackgroundColor;
	private Color myPenColor;
	private boolean penDown;
	private PenPattern myPenPattern;
	private double myPenWidth;
	
	
	public Data() {
		// TODO Auto-generated constructor stub
		myCommandHistory = new CommandHistory();
		myVariablesList = new VariablesList();
		myCharactersList = new CharactersList();
		myUserDefinedCommands = new UserDefinedCommands();
		myPenPattern = PenPattern.SOLID;
		penDown = true;
		myPenWidth = 10.0;
		myBackgroundColor = Color.WHITE;
		myPenColor = Color.BLACK;
	}

	@Override
	public CommandHistory getCommandHistory() {
		// TODO Auto-generated method stub
		return myCommandHistory;
	}

	@Override
	public VariablesList getVariablesList() {
		// TODO Auto-generated method stub
		return myVariablesList;
	}

	@Override
	public CharactersList getCharacterList() {
		// TODO Auto-generated method stub
		return myCharactersList;
	}

	@Override
	public UserDefinedCommands getUserDefinedCommands() {
		// TODO Auto-generated method stub
		return myUserDefinedCommands;
	}
	
	public void setPenPattern(PenPattern pattern){
		myPenPattern = pattern;
	}
	
	public PenPattern getPenPattern(){
		return myPenPattern;
	}
	
	public boolean getPenDown(){
		return penDown;
	}
	
	public void setPenDown(boolean penStatus){
		penDown = penStatus;
	}
	
	public Color getBackgroundColor(){
		return myBackgroundColor;
	}
	
	public void setBackgroundColor(Color color){
		myBackgroundColor = color;
	}
	
	public Color getPenColor(){
		return myPenColor;
	}
	
	public void setPenColor(Color color){
		myPenColor = color;
	}
	
	public double getPenWidth(){
		return myPenWidth;
	}
	
	public void setPenWidth(double width){
		myPenWidth = width;
	}

}
