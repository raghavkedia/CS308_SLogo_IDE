package backend;

import backend.data.CharactersList;
import backend.data.CommandHistory;
import backend.data.UserDefinedCommands;
import backend.data.VariablesList;
import exceptions.SlogoError;

public interface InterpreturInterface {
	
	public String executeCommand(String input) throws SlogoError;
	
	public CharactersList getCharacterList(String workSpaceID);
	public VariablesList getVariablesList(String workSpaceID);
	public CommandHistory getCommandHistory(String workSpaceID);
	public UserDefinedCommands getUserDefinedCommands(String workSpaceID);
	public FileGetter getFileGetter(String workSpaceID);
	
}
