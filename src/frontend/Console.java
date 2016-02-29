package frontend;

import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;

class Console extends VisualComponent{

	private TextArea commandArea;
	private History myHistory;
	
	Console(double width, double height){
		super();
		commandArea = new TextArea();
		super.setVisual(commandArea);
		handleUI();
	}
	
	void setHistory(History h) {
		myHistory = h;
	}
	
	private void handleUI() {
		commandArea.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
            switch (keyEvent.getCode()) {
            case ENTER:
            	myHistory.addToHistory(executeInput());
                break;
            default:
                break;
            }
        });		
	}

    void setText (String text) {
        commandArea.setText(text);
    }

    String executeInput() {
         String input =  commandArea.getText();
         commandArea.clear();
         return input;
    } 
}
