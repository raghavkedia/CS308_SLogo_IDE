// This entire file is part of my masterpiece.
// Jiangzhen Yu

package frontend.observer;

import java.util.Observable;
import java.util.Observer;
import controller.Controller;
import backend.data.VariablesList;

/**
 * Observes a VariablesList from the backend package. 
 * @author richardliu
 *
 */
public class VariableListObserver implements Observer{
	private VariablesList myObservable;
	private Controller myController;
	
	public VariableListObserver(VariablesList subject, Controller c){
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
