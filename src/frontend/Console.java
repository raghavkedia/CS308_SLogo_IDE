package frontend;

import controller.Controller;
import javafx.scene.control.TextArea;


class Console extends VisualComponent{

	private TextArea commandArea;
	private Controller myController;
	
	Console(double width, double height, Controller c){
		commandArea = new TextArea();
		commandArea.setPrefSize(width, height);
		super.setVisual(commandArea);
		this.myController = c;
	}
	

	/**
	 * Set the console text to the input.
	 * @param text - What is put into the console.
	 */
    void setText (String text) {	
        commandArea.appendText(text + "\n");
    }

    /**
     * Passes the current input in the console to the backend to execute.
     * @return - the output of the command
     */
    String executeInput() {
         String input =  commandArea.getText();
         commandArea.clear();
         myController.passConsoleInput(input);
         return input;
    } 
    
    void clear () {
        commandArea.clear();
    }
}
