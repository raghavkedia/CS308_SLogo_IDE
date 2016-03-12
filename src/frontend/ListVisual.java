package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

/**
 * Wrapper class for a JavaFX ListView.
 * @author richardliu
 *
 */
abstract class ListVisual extends VisualComponent implements IClickable{
	protected ListView<String> myList;
	protected ObservableList<String> myData = FXCollections.observableArrayList();

	
	public ListVisual(double width, double height){
		myList = new ListView<String>();
		myList.setPrefSize(width, height);
		this.setVisual(myList);
	}
	
	public ObservableList<String> getMyData(){
		return myData;
	}
	
	public void addToData(String content){
		myData.add(content);
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

}
