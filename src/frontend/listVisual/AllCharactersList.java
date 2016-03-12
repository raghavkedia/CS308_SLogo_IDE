package frontend.listVisual;

import controller.Controller;
import frontend.CharacterPopupWindow;
import frontend.ComponentFactory;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AllCharactersList extends ListVisual {

	
	public AllCharactersList(double width, double height, Controller controller) {
		super(width, height, controller);
	}

	/**
	 * On a double click, create a PopupWindow to change variables.
	 */
	@Override
	public void respondToClick() {
		CharacterPopupWindow popup = ComponentFactory.makeNewCharactersPopupWindow(getMyList().getSelectionModel().getSelectedItem(), 
																				   getMyController());
		ComponentFactory.initNewPopup(popup, 300, 700);
	}
}
