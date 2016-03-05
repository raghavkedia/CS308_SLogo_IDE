package frontend;

import controller.Controller;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


class History extends ListVisual{
	private int historyPointer;
	private Controller myController;
	
	History(double width, double height, Controller c){
		super(width, height);
		this.myList.setItems(myData);
		historyPointer = 0;
		handleUI();
		myController = c;
	}

	/**
	 * Pushes a command to the history as the most recent command.
	 * @param pastCommand
	 */
	void addToHistory(String pastCommand){
		myData.add(pastCommand);
	}
	
    private void handleUI() {
        this.myList.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            switch (keyEvent.getCode()) {
                case UP:
                	if (historyPointer > 0)
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
            System.out.println(historyPointer);
            historyPointer = ((historyPointer % myData.size())+myData.size()) % myData.size();
            myController.displayInConsole(myData.get(historyPointer));
        });
        this.myList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	myController.displayInConsole(myList.getSelectionModel().getSelectedItem());
                historyPointer = myList.getSelectionModel().getSelectedIndex();              
            }
        });
    }
    
    /**
     * Resets the historyPointer variable. 
     * Use case: if the history of commands is ever updated, historyPointer should be reset.
     */
    public void resetHistoryPointer(){historyPointer = 0;}
}
