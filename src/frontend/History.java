package frontend;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyEvent;

public class History extends ListVisual{
	private int historyPointer = 0;
	
	public History(double width, double height){
		super(width, height);
		myData.add("World");
		this.myList.setItems(myData);
	}

	public void addToHistory(String pastCommand){
		myData.add(pastCommand);
	}
	
    public void handleUI() {
        this.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    if (historyPointer == 0) {
                        break;
                    }
                    historyPointer--;
                    break;
                case DOWN:
                    if (historyPointer == history.size() - 1) {
                        break;
                    }
                    historyPointer++;
                    break;
                default:
                    break;
            }
        });
        //setText(pastCommands.get(historyPointer));
    }
}
