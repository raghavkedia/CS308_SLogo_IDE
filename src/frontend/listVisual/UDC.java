package frontend.listVisual;

import controller.Controller;

public class UDC extends ListVisual {
	
	public UDC(double width, double height, Controller controller){
		super(width, height, controller);
	}
	
	/**
	 * On a double click, display the clicked item in the Console.
	 */
	@Override
	public void respondToClick() {
		String selected = getMyList().getSelectionModel().getSelectedItem();
		getMyController().displayInConsole(selected);
	}

}
