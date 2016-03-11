package frontend;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UDC extends ListVisual implements IClickable{

Controller myController;
	
	public UDC(double width, double height, Controller control){
		super(width, height);
		this.myList.setItems(myData);
		myController = control;
		
		initMouseHandler();
	}
	
	/**
	 * Adds a String to the list of items.
	 * @param newCommand - String received from backend
	 */
	public void addToUDC(String newCommand){
		myData.add(newCommand);
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
	 * On a double click, display the clicked item in the Console.
	 */
	@Override
	public void respondToClick() {
		String selected = myList.getSelectionModel().getSelectedItem();
		myController.displayInConsole(selected);
	}

}
