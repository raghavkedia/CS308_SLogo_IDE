// This entire file is part of my masterpiece.
// Jiangzhen Yu

package frontend.observer;

import controller.Controller;
import backend.data.UserDefinedCommands;

import java.util.Observable;
import java.util.Observer;

public class UDCObserver implements Observer{
	private UserDefinedCommands myObservable;
	private Controller myController;
	
	public UDCObserver(UserDefinedCommands subject, Controller c){
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