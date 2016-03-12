package frontend.listVisual;

import controller.Controller;


public class History extends ListVisual{
	
	public History(double width, double height, Controller controller) {
		super(width, height, controller);
	}
    
    /**
     * Resets the historyPointer variable. 
     * Use case: if the history of commands is ever updated, historyPointer should be reset.
     */
//    public void resetHistoryPointer(){historyPointer = 0;}

	@Override
	public void respondToClick() {
		getMyController().displayInConsole(getMyList().getSelectionModel().getSelectedItem());		
	}
}
