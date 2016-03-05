package frontend;

import backend.*;
import backend.data.Character;
import backend.data.CharactersList;

import java.util.Observable;
import java.util.Observer;

public class CharacterListObserver implements Observer{
	CharactersList myObservable;
	
	CharacterListObserver(CharactersList subject){
		myObservable = subject;
		subject.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		FrontendManagerAPI.clearCharactersFromFrontend();
		for (Character c : myObservable.getCharacters()){
			FrontendManagerAPI.addPortrait(c);
		}
	}

}
