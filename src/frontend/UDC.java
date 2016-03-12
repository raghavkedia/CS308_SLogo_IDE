package frontend;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UDC extends ListVisual {

Controller myController;
	
	public UDC(double width, double height, Controller control){
		super(width, height);
		this.myList.setItems(myData);
		myController = control;		
		initMouseHandler();
	}
	
	/**
	 * On a double click, display the clicked item in the Console.
	 */
	@Override
	public void respondToClick() {
		String selected = myList.getSelectionModel().getSelectedItem();
		myController.displayInConsole(selected);
	}

}
