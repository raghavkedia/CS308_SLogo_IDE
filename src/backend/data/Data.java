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
	private Properties myProperties;
	private ColorMap myColorMap;
	private ShapeMap myShapeMap;
	private boolean clearScreen;
	
	public Data() {
		// TODO Auto-generated constructor stub
		myCommandHistory = new CommandHistory();
		myVariablesList = new VariablesList();
		myCharactersList = new CharactersList();
		myUserDefinedCommands = new UserDefinedCommands();
		myProperties = new Properties();
		myColorMap = new ColorMap();
		myShapeMap = new ShapeMap();
		clearScreen = false;
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
	
	public Properties getProperties(){
		return myProperties;
	}
	
	public ColorMap getColorMap(){
		return myColorMap;
	}
	
	public ShapeMap getShapeMap(){
		return myShapeMap;
	}
	
	public void setClearScreen(boolean c){
		clearScreen = c;
	}
	
	public boolean isClearScreen(){
		return clearScreen;
	}
}
