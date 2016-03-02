package frontend;

import javafx.scene.control.TextArea;


class Console extends VisualComponent{

	private TextArea commandArea;
	
	Console(double width, double height){
		super();
		commandArea = new TextArea();
		super.setVisual(commandArea);
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
         FrontendManagerAPI.passConsoleInput(input);
         return input;
    } 
    
    void clear () {
        commandArea.clear();
    }
}
