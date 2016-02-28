package frontend;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Variables extends ListVisual implements IClickable{
	
	public Variables(double width, double height){
		super(width, height);
		myData.add("Hello=World");
		myData.add("Variable=Value");
		this.myList.setItems(myData);
		
		initMouseHandler();
	}
	
	/**
	 * Adds a String to the list of items.
	 * @param newVar - String received from backend
	 */
	public void addToVariables(String newVar){
		myData.add(newVar);
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
		System.out.println("clicked on " + myList.getSelectionModel().getSelectedItem());
		
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        PopupWindow popup = ComponentFactory.makeNewPopupWindow(myList.getSelectionModel().getSelectedItem());
        Scene dialogScene = new Scene(popup.getMyBox(), 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}
}
