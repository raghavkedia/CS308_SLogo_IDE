package frontend;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AllCharactersList extends ListVisual {
	Controller myController;
	
	public AllCharactersList(double width, double height, Controller control){
		super(width, height);
		this.myList.setItems(myData);
		myController = control;
		
		initMouseHandler();
	}
	
	/**
	 * On a double click, create a PopupWindow to change variables.
	 */
	@Override
	public void respondToClick() {
		CharacterPopupWindow popup = ComponentFactory.makeNewCharactersPopupWindow(myList.getSelectionModel().getSelectedItem(), myController);
		ComponentFactory.initNewPopup(popup, 300, 700);
	}
}
