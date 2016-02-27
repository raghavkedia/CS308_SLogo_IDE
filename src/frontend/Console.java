package frontend;

import javafx.scene.control.TextArea;

public class Console extends VisualComponent{
	private TextArea commandArea;
	
	public Console(double width, double height){
		super();
		commandArea = new TextArea();
		super.setVisual(commandArea);
	}

    public void setText (String text) {
        commandArea.setText(text);
    }

    public String executeInput() {
         String input =  commandArea.getText();
         commandArea.clear();
         return input;
    } 
}
