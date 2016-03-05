package frontend;

import java.util.Observable;
import java.util.Observer;
import backend.*;
import controller.Controller;
import backend.data.CommandHistory;

/**
 * Observes a CommandHistory from the backend package. 
 * @author richardliu
 *
 */

public class HistoryListObserver implements Observer{
	CommandHistory myHistory;
	Controller myController;
	
	HistoryListObserver(CommandHistory subject, Controller c){
		this.myHistory = subject;
		myHistory.addObserver(this);
		this.myController = c;
	}
	
	/**
	 * Updates frontend to mirror backend.
	 * When the backend changes, clear the frontend and redisplay the entire backend.
	 */
	@Override
	public void update(Observable o, Object arg) {
		myController.clearHistoryFrontend();
		for (String s : myHistory.getPastCommands()){
			myController.addToHistory(s);
		}
	}
	
}
