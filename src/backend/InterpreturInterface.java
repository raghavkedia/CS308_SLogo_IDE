package backend;

public interface InterpreturInterface {
	
	public String executeCommand(String input) throws Exception;
	
	public CharactersList getCharacterList();
	public VariablesList getVariablesList();
	public CommandHistory getCommandHistory();
}
