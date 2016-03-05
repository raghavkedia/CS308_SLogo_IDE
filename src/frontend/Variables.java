package frontend;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Variables extends ListVisual implements IClickable{
	Controller myController;
	
	public Variables(double width, double height, Controller control){
		super(width, height);
		this.myList.setItems(myData);
		myController = control;
		
		initMouseHandler();
	}
	
	/**
	 * Adds a String to the list of items.
	 * @param newVar - String received from backend
	 */
	public void addToVariables(String newVar){
		myData.add(newVar);
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
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        PopupWindow popup = ComponentFactory.makeNewVariablesPopupWindow(myList.getSelectionModel().getSelectedItem(), myController);
        Scene dialogScene = new Scene(popup.getMyBox(), 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}
}
