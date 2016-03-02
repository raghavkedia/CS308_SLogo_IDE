package backend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public class CharactersList extends Observable implements Observer{
	
	private Map<String, Character> characters;
	private List<String> activeCharacters;
	
	public CharactersList() {
		// TODO Auto-generated constructor stub
		characters = new HashMap<String, Character>();
		activeCharacters = new ArrayList<String>();
	}

	public void addCharacter(Character character){
		characters.put(character.getName(), character);
		character.addObserver(this);
	}
	
	public Character getCharacter(String characterKey){
		return characters.get(characterKey);
	}
	
	public void hasUpdated(){
		setChanged();
		notifyObservers(this);
	}
	
	public List<String> getActiveCharacters(){
		return activeCharacters;
	}
	
	public void addActiveCharcter(String id){
		activeCharacters.add(id);
	}
	
	public void removeActiveCharacter(String id){
		activeCharacters.remove(id);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		hasUpdated();
	}
	
}
