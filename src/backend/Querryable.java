package backend;

import java.util.List;
import java.util.Map;

import backend.data.Character;

public interface Querryable {

	public List<String> getCommandHistory();
	public Map<String, Integer> getVariables();
	public List<Character> getAllCharacters();
	public void addCharacter(Character c);
	
}
