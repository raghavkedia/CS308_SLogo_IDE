package backend;

import backend.data.CharactersList;
import backend.data.CommandHistory;
import backend.data.Properties;
import backend.data.UserDefinedCommands;
import backend.data.VariablesList;
import exceptions.SlogoError;

public interface InterpreturInterface {
	
	public String executeCommand(String input, int workspaceID) throws SlogoError;
	
	public CharactersList getCharacterList(String workSpaceID);
	public VariablesList getVariablesList(String workSpaceID);
	public CommandHistory getCommandHistory(String workSpaceID);
	public UserDefinedCommands getUserDefinedCommands(String workSpaceID);
	public Properties getProperties(String workSpaceID);
	public FileGetter getFileGetter(String workSpaceID);
	
}
