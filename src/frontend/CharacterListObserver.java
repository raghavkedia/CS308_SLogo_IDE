package frontend;

import controller.Controller;
import exceptions.InvalidCharacterError;
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
		try {
			updateCharacterDisplay();
		} catch (InvalidCharacterError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateCharacterDisplay() throws InvalidCharacterError{
		myController.clearCharactersFromFrontend();
		myController.clearAllChars();
		for (Character c : myObservable.getCharacters()){
			myController.addPortrait(c);
			myController.addChar(c);
		}
	}

}
