package frontend;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


class History extends ListVisual{
	private int historyPointer = 0;
	private Console myConsole;
	History(double width, double height){
		super(width, height);
		this.myList.setItems(myData);
		handleUI();
	}

	void addToHistory(String pastCommand){
		myData.add(pastCommand);
	}

	void setConsole(Console c) {
		myConsole = c;
	}
	
    private void handleUI() {
        this.myList.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                    if (historyPointer == 0) {
                        break;
                    }
                    historyPointer--;
                    break;
                case DOWN:
                    if (historyPointer == myData.size() - 1) {
                        break;
                    }
                    historyPointer++;
                    break;
                default:
                    break;
            }
            myConsole.setText(myData.get(historyPointer));
        });
        this.myList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	myConsole.setText(myList.getSelectionModel().getSelectedItem());
                historyPointer = myList.getSelectionModel().getSelectedIndex();              
            }
        });
    }
}
