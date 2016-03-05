package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import backend.data.CharactersList;
import backend.data.CommandHistory;
import backend.data.Data;
import backend.data.Properties;
import backend.data.UserDefinedCommands;
import backend.data.VariablesList;
import backend.parser.Parseable;
import backend.parser.SimpleSplitParse;
import exceptions.InvalidQuotientError;
import exceptions.SlogoError;

public class BackendManager implements InterpreturInterface{
	
	private Parseable myParser;
	private CommandHistory myCommandHistory;
	private VariablesList myVariablesList;
	private CharactersList myCharactersList;
    private ResourceBundle myResources;
    private UserDefinedCommands myUserDefinedCommands;
    private FileHandler myFileHandler;
    
    //for multiple workspaces, just a concept
    private Map<Integer, Data> myWorkSpaces;
    
	
	public BackendManager() {
		myParser = new SimpleSplitParse("English");
		myCommandHistory = new CommandHistory();
		myVariablesList = new VariablesList();
		myCharactersList = new CharactersList();
		myUserDefinedCommands = new UserDefinedCommands();
		myWorkSpaces = new HashMap<Integer, Data>();
		
		//initial workspace
		myWorkSpaces.put(0, new Data());
	}
	
	public String executeCommand(String input, int workspaceID) throws SlogoError{
		
		myWorkSpaces.get(workspaceID).getCommandHistory().addCommand(input);
		
		String output = "error";
		
		output = myParser.runInput(input, myWorkSpaces.get(workspaceID), myResources);
		return output;
		
	}
	
	public CharactersList getCharacterList(int workSpaceID){
		return myWorkSpaces.get(workSpaceID).getCharacterList();
	}
	public VariablesList getVariablesList(int workSpaceID){
		return myWorkSpaces.get(workSpaceID).getVariablesList();
	}
	public CommandHistory getCommandHistory(int workSpaceID){
		return myWorkSpaces.get(workSpaceID).getCommandHistory();
	}
	public UserDefinedCommands getUserDefinedCommands(int workSpaceID) {
		// TODO Auto-generated method stub
		return null;
	}	
	public void addWorkSpace(int newID){
		myWorkSpaces.put(newID, new Data());
	}
	
	public FileGetter getFileGetter(int workSpaceID) {
		// TODO Auto-generated method stub
		return myFileHandler;
	}
	
	public Properties getProperties(int workSpaceID){
		return myWorkSpaces.get(workSpaceID).getProperties();
	}
}


