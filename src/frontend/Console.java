package frontend;

import javafx.event.EventHandler;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

class Console extends VisualComponent{

	private TextArea commandArea;
	
	Console(double width, double height){
		super();
		commandArea = new TextArea();
		super.setVisual(commandArea);
//		handleUI();
	}
	
//	public EventHandler handleUI() {
//		commandArea.addEventHandler(KeyEvent.KEY_RELEASED, keyEvent -> {
//            switch (keyEvent.getCode()) {
//            case ENTER:
//            	executeInput();
//                break;
//            default:
//                break;
//            }
//        });
//		
//		new EventHandler<KeyEvent>() {
//            public void handle(final KeyEvent keyEvent) {
//                if (keyEvent.getCode() == KeyCode.ENTER) {
//                    setPressed(keyEvent.getEventType()
//                        == KeyEvent.KEY_PRESSED);
// 
//                    keyEvent.consume();
//                }
//            }
//        };
//	}

	/**
	 * Set the console text to the input.
	 * @param text - What is put into the console.
	 */
    void setText (String text) {
        commandArea.setText(text);
    }

    /**
     * Passes the current input in the console to the backend to execute.
     * @return - the output of the command
     */
    String executeInput() {
         String input =  commandArea.getText();
         commandArea.clear();
         FrontendManagerAPI.passConsoleInput(input);
         return input;
    } 
    
    void clear () {
        commandArea.clear();
    }
}
