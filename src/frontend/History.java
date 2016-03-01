package frontend;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;


class History extends ListVisual{
	private int historyPointer;
	History(double width, double height){
		super(width, height);
		this.myList.setItems(myData);
		historyPointer = 0;
		handleUI();
	}

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
            FrontendManagerAPI.displayInConsole(myData.get(historyPointer));
        });
        this.myList.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            	FrontendManagerAPI.displayInConsole(myList.getSelectionModel().getSelectedItem());
                historyPointer = myList.getSelectionModel().getSelectedIndex();              
            }
        });
    }
    
    public void resetHistoryPointer(){historyPointer = 0;}
}
