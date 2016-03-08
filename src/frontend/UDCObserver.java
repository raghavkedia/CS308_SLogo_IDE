package frontend;

import controller.Controller;
import backend.data.UserDefinedCommands;

import java.util.Observable;
import java.util.Observer;

public class UDCObserver implements Observer{
	UserDefinedCommands myObservable;
	Controller myController;
	
	UDCObserver(UserDefinedCommands subject, Controller c){
		myObservable = subject;
		subject.addObserver(this);
		this.myController = c;
	}

	@Override
	public void update(Observable o, Object arg) {
		myController.clearUDC();
		for (String command : myObservable.getUserDefinedCommands().keySet()){
			myController.addCommandToUDC(command);
		}
	}

}