package frontend;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


public class History extends ListVisual{
	private Controller myController;
	
	public History(double width, double height, Controller c){
		super(width, height);
		this.myList.setItems(myData);
		myController = c;
		initMouseHandler();
	}

    
    /**
     * Resets the historyPointer variable. 
     * Use case: if the history of commands is ever updated, historyPointer should be reset.
     */
//    public void resetHistoryPointer(){historyPointer = 0;}

	@Override
	public void respondToClick() {
		myController.displayInConsole(myList.getSelectionModel().getSelectedItem());		
	}
}
