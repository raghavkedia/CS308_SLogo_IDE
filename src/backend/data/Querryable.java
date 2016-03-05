package backend.data;

import java.util.List;
import java.util.Map;

public interface Querryable {

	public CommandHistory getCommandHistory();
	public VariablesList getVariablesList();
	public CharactersList getCharacterList();
	public UserDefinedCommands getUserDefinedCommands();

	
}
