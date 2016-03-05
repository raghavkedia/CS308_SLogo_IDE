package frontend;

import backend.Character;
import controller.Controller;
import backend.*;
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
		myController.clearCharactersFromFrontend();
		for (Character c : myObservable.getCharacters()){
			myController.addPortrait(c);
		}
	}

}
