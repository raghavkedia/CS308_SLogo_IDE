package frontend;

import backend.Character;
import backend.*;
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
			System.out.println(c.toString());
			System.out.println(c.getCoordX());
			System.out.println(c.getCoordY());
			System.out.println("end");
			FrontendManagerAPI.addPortrait(c);
		}
	}

}
