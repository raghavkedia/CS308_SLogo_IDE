package frontend;

import javafx.scene.control.TextArea;

class Console extends VisualComponent{
	private TextArea commandArea;
	
	Console(double width, double height){
		super();
		commandArea = new TextArea();
		super.setVisual(commandArea);
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
