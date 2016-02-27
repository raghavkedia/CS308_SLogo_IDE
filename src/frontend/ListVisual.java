package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

abstract class ListVisual extends VisualComponent{
	protected ListView<String> myList;
	protected ObservableList<String> myData = FXCollections.observableArrayList();

	
	public ListVisual(double width, double height){
		myList = new ListView<String>();
		myList.setPrefHeight(height);
		myList.setPrefWidth(width);
		this.setVisual(myList);
	}
	
	
}
