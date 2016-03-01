package frontend;

import java.util.Observable;
import java.util.Observer;
import backend.*;

public class HistoryListObserver implements Observer{
	CommandHistory myHistory;
	
	HistoryListObserver(CommandHistory subject){
		this.myHistory = subject;
		myHistory.addObserver(this);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		FrontendManagerAPI.clearHistory();
		for (String s : myHistory.getPastCommands()){
			FrontendManagerAPI.addToHistory(s);
		}
	}
	
}
