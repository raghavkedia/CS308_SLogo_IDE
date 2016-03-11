package backend.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;
import java.util.Set;

import exceptions.InvalidCharacterError;

public class CharactersList extends Observable implements Observer{
	
	private Map<String, Character> characters;
	private List<String> activeCharacters;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private ResourceBundle myErrorResources;
	
	public CharactersList() {
		// TODO Auto-generated constructor stub
		characters = new HashMap<String, Character>();
		activeCharacters = new ArrayList<String>();
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
	}

	public void addCharacter(Character character){
		characters.put(character.getName(), character);
		activeCharacters.add(character.getName());
		character.addObserver(this);
		hasUpdated();
	}
	
	public Character getCharacter(String characterKey) throws InvalidCharacterError{
		if(!characterExists(characterKey)){
			throw new InvalidCharacterError(myErrorResources.getString("InvalidCharacterError"));
		}
		return characters.get(characterKey);
	}
	
	public void hasUpdated(){
		setChanged();
		notifyObservers(this);
	}
	
	public List<String> getActiveCharacters(){
		return activeCharacters;
	}
	
	public List<Character> getAllActiveCharacters(){
		ArrayList<Character> out = new ArrayList<Character>();
		for (String charName : characters.keySet()){
			if (activeCharacters.contains(charName)) out.add(characters.get(charName));
		}
		return out;
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
	
	public Collection<Character> getCharacters(){
		return this.characters.values();
	}
	
	public double getNumCharacters(){
		return characters.size();
	}
	
	public boolean characterExists(String key){
		if(characters.get(key) == null){
			return false;
		}
		return true;
	}
	
}
