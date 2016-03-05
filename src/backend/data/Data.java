package backend.data;

import java.util.List;
import java.util.Map;

public class Data implements Querryable{
	
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

	@Override
	public CommandHistory getCommandHistory() {
		// TODO Auto-generated method stub
		return myCommandHistory;
	}

	@Override
	public VariablesList getVariablesList() {
		// TODO Auto-generated method stub
		return myVariablesList;
	}

	@Override
	public CharactersList getCharacterList() {
		// TODO Auto-generated method stub
		return myCharactersList;
	}

	@Override
	public UserDefinedCommands getUserDefinedCommands() {
		// TODO Auto-generated method stub
		return myUserDefinedCommands;
	}
	


}
