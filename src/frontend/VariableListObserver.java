package frontend;

import java.util.Observable;
import java.util.Observer;
import backend.*;

public class VariableListObserver implements Observer{
	VariablesList myObservable;
	
	VariableListObserver(VariablesList subject){
		myObservable = subject;
		subject.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		FrontendManagerAPI.clearVariables();
		for (String key : myObservable.getVariables().keySet()){
			String displayMe = key+"="+myObservable.getVariables().get(key);
			FrontendManagerAPI.addToVariables(displayMe);
		}
	}
}
