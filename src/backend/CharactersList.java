package backend;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class CharactersList extends Observable{
	
	private Map<String, Character> characters;
	
	public CharactersList() {
		// TODO Auto-generated constructor stub
		characters = new HashMap<String, Character>();
	}

	public void addCharacter(Character character){
		characters.put(character.getName(), character);
	}
	
	public Character getCharacter(String characterKey){
		return characters.get(characterKey);
	}
	
	public void hasUpdated(){
		setChanged();
		notifyObservers(this);
	}
	
}
