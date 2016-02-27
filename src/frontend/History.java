package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class History extends ListVisual{
	
	public History(double width, double height){
		super(width, height);
		myData.add("World");
		this.myList.setItems(myData);
	}

	public void addToHistory(String pastCommand){
		myData.add(pastCommand);
	}
}
