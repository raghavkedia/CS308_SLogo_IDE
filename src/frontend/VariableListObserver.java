package frontend;

import java.util.Observable;
import java.util.Observer;
import backend.*;
import backend.data.VariablesList;

/**
 * Observes a VariablesList from the backend package. 
 * @author richardliu
 *
 */
public class VariableListObserver implements Observer{
	VariablesList myObservable;
	
	VariableListObserver(VariablesList subject){
		myObservable = subject;
		subject.addObserver(this);
	}

	/**
	 * Updates frontend to mirror backend.
	 * When the backend changes, clear the frontend and redisplay the entire backend.
	 */
	@Override
	public void update(Observable o, Object arg) {
		FrontendManagerAPI.clearVariables();
		for (String key : myObservable.getVariables().keySet()){
			String displayMe = key+"="+myObservable.getVariables().get(key).getVariableValue();
			FrontendManagerAPI.addToVariables(displayMe);
		}
	}
}
