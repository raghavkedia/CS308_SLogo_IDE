package frontend.listVisual;

import controller.Controller;
import frontend.VisualComponent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public abstract class ListVisual extends VisualComponent implements IClickable{
	private ListView<String> myList;
	private ObservableList<String> myData;
	private Controller myController;
	
	public ListVisual(double width, double height, Controller controller){
		this.myList = new ListView<String>();
		this.myList.setPrefSize(width, height);
		this.setVisual(myList);
		this.myData =  FXCollections.observableArrayList();
		this.myList.setItems(myData);
		this.myController = controller;
		initMouseHandler();
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
	
	//Getter
	protected ListView<String> getMyList() {return myList;}
	protected Controller getMyController() {return myController;}
	
	public int size() {return myData.size();}
}
