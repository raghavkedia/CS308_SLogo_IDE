package backend;

import exceptions.SlogoError;

public interface InterpreturInterface {
	
	public String executeCommand(String input) throws SlogoError;
	
	public CharactersList getCharacterList();
	public VariablesList getVariablesList();
	public CommandHistory getCommandHistory();
	public FileGetter getFileGetter();
	
}
