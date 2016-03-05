package frontend;

import java.util.Observable;
import java.util.Observer;
import backend.*;
import backend.data.CommandHistory;

/**
 * Observes a CommandHistory from the backend package. 
 * @author richardliu
 *
 */

public class HistoryListObserver implements Observer{
	CommandHistory myHistory;
	
	HistoryListObserver(CommandHistory subject){
		this.myHistory = subject;
		myHistory.addObserver(this);
	}
	
	/**
	 * Updates frontend to mirror backend.
	 * When the backend changes, clear the frontend and redisplay the entire backend.
	 */
	@Override
	public void update(Observable o, Object arg) {
		FrontendManagerAPI.clearHistory();
		for (String s : myHistory.getPastCommands()){
			FrontendManagerAPI.addToHistory(s);
		}
	}
	
}
