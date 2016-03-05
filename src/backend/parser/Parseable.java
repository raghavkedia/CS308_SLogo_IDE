package backend.parser;

import java.util.Collection;
import java.util.ResourceBundle;

import backend.data.CharactersList;
import backend.data.Data;
import backend.data.UserDefinedCommands;
import backend.data.VariablesList;
import exceptions.SlogoError;

public interface Parseable {
	
	public String runInput(String input, Data myData, ResourceBundle myResources) throws SlogoError;
	public Collection<String> stringParse(String input);
	
}
