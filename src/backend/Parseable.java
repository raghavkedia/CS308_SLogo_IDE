package backend;

import java.util.Collection;
import java.util.ResourceBundle;

public interface Parseable {
	
	public ParsedInput parse(String input);
	public String runInput(String input, CharactersList myCharactersList, VariablesList myVariablesList, CommandHistory myCommandHistroy, ResourceBundle myResources);
	public Collection<String> stringParse(String input);
	
}
