package backend;

import java.util.Collection;
import java.util.ResourceBundle;

import exceptions.SlogoError;

public interface Parseable {
	
	public String runInput(String input, CharactersList myCharactersList, VariablesList myVariablesList, UserDefinedCommands myUserDefinedCommands, ResourceBundle myResources) throws SlogoError;
	public Collection<String> stringParse(String input);
	
}
