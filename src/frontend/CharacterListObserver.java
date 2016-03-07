package frontend;

import controller.Controller;
import backend.*;
import backend.data.Character;
import backend.data.CharactersList;

import java.util.Observable;
import java.util.Observer;

public class CharacterListObserver implements Observer{
	CharactersList myObservable;
	Controller myController;
	
	CharacterListObserver(CharactersList subject, Controller c){
		myObservable = subject;
		subject.addObserver(this);
		this.myController = c;
	}

	@Override
	public void update(Observable o, Object arg) {
		updateCharacterDisplay();
		updateAllCharactersList();
	}
	
	public void updateCharacterDisplay(){
		myController.clearCharactersFromFrontend();
		for (Character c : myObservable.getCharacters()){
			myController.addPortrait(c);
		}
	}
	
	public void updateAllCharactersList(){
		myController.clearAllChars();
		for (Character c : myObservable.getAllActiveCharacters()){
			myController.addChar(c);
		}
	}

}
