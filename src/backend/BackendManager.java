package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import backend.data.CharactersList;
import backend.data.CommandHistory;
import backend.data.Data;
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
    private Map<String, Data> myWorkSpaces;
    
	
	public BackendManager() {
		myParser = new SimpleSplitParse("English");
		myCommandHistory = new CommandHistory();
		myVariablesList = new VariablesList();
		myCharactersList = new CharactersList();
		myUserDefinedCommands = new UserDefinedCommands();
		myWorkSpaces = new HashMap<String, Data>();
	}
	
	public String executeCommand(String input) throws SlogoError{
		
		myCommandHistory.addCommand(input);
		
		String output = "error";
		
		output = myParser.runInput(input, myCharactersList, myVariablesList, myUserDefinedCommands, myResources);
		return output;
		
	}
	
	public CharactersList getCharacterList(){
		return myCharactersList;
	}
	public VariablesList getVariablesList(){
		return myVariablesList;
	}
	public CommandHistory getCommandHistory(){
		return myCommandHistory;
	}

	public FileGetter getFileGetter() {
		// TODO Auto-generated method stub
		return myFileHandler;
	}
	
}


