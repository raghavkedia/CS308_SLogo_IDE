package backend;

public class Data {
	
	private CommandHistory myCommandHistory;
	private VariablesList myVariablesList;
	private CharactersList myCharactersList;
	private UserDefinedCommands myUserDefinedCommands;
	
	public Data() {
		// TODO Auto-generated constructor stub
		myCommandHistory = new CommandHistory();
		myVariablesList = new VariablesList();
		myCharactersList = new CharactersList();
		myUserDefinedCommands = new UserDefinedCommands();
	}

}
