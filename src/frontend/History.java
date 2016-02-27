package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class History extends ListVisual{
	
	private ObservableList<String> pastCommands = FXCollections.observableArrayList();
	
	public History(double width, double height){
		super(width, height);
		this.myList.setItems(pastCommands);
	}

	public void addToHistory(String pastCommand){
		pastCommands.add(pastCommand);
	}
	
}
