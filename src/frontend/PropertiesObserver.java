package frontend;

import java.util.Observable;
import java.util.Observer;

import backend.data.Properties;
import controller.Controller;

public class PropertiesObserver implements Observer{
	Properties myObservable;
	Controller myController;
	
	PropertiesObserver(Properties subject, Controller c){
		myObservable = subject;
		subject.addObserver(this);
		this.myController = c;
	}

	@Override
	public void update(Observable o, Object arg) {
		if (myObservable.isClearScreen()){
			myController.clearAllLines();
		}
		myObservable.setClearScreen(false);
	}

}