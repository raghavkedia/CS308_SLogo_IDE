package frontend;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AllCharactersList extends ListVisual implements IClickable{
	Controller myController;
	
	public AllCharactersList(double width, double height, Controller control){
		super(width, height);
		this.myList.setItems(myData);
		myController = control;
		
		initMouseHandler();
	}
	
	/**
	 * Adds a String to the list of items.
	 * @param newVar - String received from backend
	 */
	public void addToAllChars(String newChar){
		myData.add(newChar);
	}
	
	public void clearAll(){
		myData.clear();
	}
	
	/**
	 * This method creates a mouse onclick event for each of the list items.
	 */
	public void initMouseHandler(){
		myList.setOnMouseClicked(new EventHandler<MouseEvent>() {

	        @Override
	        public void handle(MouseEvent mouseEvent) {
	            if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
	                if(mouseEvent.getClickCount() == 2){
	                    respondToClick();
	                }
	            }
	        }
	    });
		
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
