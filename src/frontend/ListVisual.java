package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

/**
 * Wrapper class for a JavaFX ListView.
 * @author richardliu
 *
 */
abstract class ListVisual extends VisualComponent{
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
}
