/**
 * This is part of my code masterpiece.
 * The responsibility of this class is to implement the Observer interface and observe a class on the backend, the class that holds the data for the Variable information;
 * I included this in my masterpiece because I refactored this class to become more closed. Previously, it stored a copy of backend.VariablesList because it required information on how the data was stored in the backend.
 * I refactored this class to cast the interface to that class instead and threw an error otherwise. This class no longer requires direct knowledge of how the backend stores the variables.
 */

package frontend;

import java.util.Observable;
import java.util.Observer;
import backend.*;
import controller.Controller;
import frontend.listVisual.Variables;
import backend.data.VariablesList;

/**
 * Observes a VariablesList from the backend package. 
 * @author richardliu
 *
 */
public class VariableListObserver implements Observer{
	Controller myController;
	public static String EQUALS = "=";
	
	VariableListObserver(Observable subject, Controller c){
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
		
		if (o.getClass().getName().equals("java.backend.VariablesList")){
			VariablesList myObserved = (VariablesList) o;
			
			for (String key : myObserved.getVariables().keySet()){
				String displayMe = key+EQUALS+myObserved.getVariables().get(key).getVariableValue();
				myController.addToVariables(displayMe);
			}
		}
		else{
			throw new RuntimeException();
		}
	}
}
