package backend;

import backend.data.CharactersList;
import backend.data.CommandHistory;
import backend.data.Properties;
import backend.data.UserDefinedCommands;
import backend.data.VariablesList;
import exceptions.SlogoError;

public interface InterpreturInterface {
	
	public String executeCommand(String input, int workspaceID) throws SlogoError;
	
	public CharactersList getCharacterList(int workSpaceID);
	public VariablesList getVariablesList(int workSpaceID);
	public CommandHistory getCommandHistory(int workSpaceID);
	public UserDefinedCommands getUserDefinedCommands(int workSpaceID);
	public Properties getProperties(int workSpaceID);
	public FileGetter getFileGetter(String workSpaceID);
	
}
