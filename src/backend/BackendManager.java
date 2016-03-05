package backend;

import java.util.ResourceBundle;

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
    
	
	public BackendManager() {
		myParser = new SimpleSplitParse("English");
		myCommandHistory = new CommandHistory();
		myVariablesList = new VariablesList();
		myCharactersList = new CharactersList();
		myUserDefinedCommands = new UserDefinedCommands();
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


