package frontend.listVisual;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
