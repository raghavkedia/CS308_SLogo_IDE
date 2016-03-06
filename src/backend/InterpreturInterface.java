package backend;

import backend.data.CharactersList;
import backend.data.CommandHistory;
import backend.data.Data;
import backend.data.Properties;
import backend.data.UserDefinedCommands;
import backend.data.VariablesList;
import exceptions.SlogoError;

public interface InterpreturInterface {
	
	public String executeCommand(String input, int workspaceID) throws SlogoError;
	
	public CharactersList getCharacterList(int workSpaceID);
	public VariablesList getVariablesList(int workspaceId);
	public CommandHistory getCommandHistory(int workspaceId);
	public UserDefinedCommands getUserDefinedCommands(int workspaceId);
	public Properties getProperties(int workspaceId);
	public FileGetter getFileGetter(String workSpaceID);

	public void addWorkSpace(int i);
	
}
