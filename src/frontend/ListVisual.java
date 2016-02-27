package frontend;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

abstract class ListVisual extends VisualComponent{
	protected ListView<String> myList;
	
	public ListVisual(double width, double height){
		myList = new ListView<String>();
		myList.setPrefHeight(height);
		myList.setPrefWidth(width);
		this.setVisual(myList);
	}
}
