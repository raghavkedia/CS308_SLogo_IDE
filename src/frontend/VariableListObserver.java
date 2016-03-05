package frontend;

import java.util.Observable;
import java.util.Observer;
import backend.*;
import controller.Controller;

/**
 * Observes a VariablesList from the backend package. 
 * @author richardliu
 *
 */
public class VariableListObserver implements Observer{
	VariablesList myObservable;
	Controller myController;
	
	VariableListObserver(VariablesList subject, Controller c){
		myObservable = subject;
		subject.addObserver(this);
		this.myController = c;
	}

	/**
	 * Updates frontend to mirror backend.
	 * When the backend changes, clear the frontend and redisplay the entire backend.
	 */
	@Override
	public void update(Observable o, Object arg) {
		myController.clearVariablesFrontend();
		for (String key : myObservable.getVariables().keySet()){
			String displayMe = key+"="+myObservable.getVariables().get(key).getVariableValue();
			myController.addToVariables(displayMe);
		}
	}
}
