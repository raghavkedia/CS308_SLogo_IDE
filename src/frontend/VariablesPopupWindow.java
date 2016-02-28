package frontend;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class VariablesPopupWindow extends PopupWindow {
	
	public VariablesPopupWindow(String myDisplay){
		super();
		initBox(myDisplay);
	}
	
	public void initBox(String display){
		String[] myVarInfo = display.split("=");
		//TODO: add error checking
		if (myVarInfo.length != 2) {
			// throw some error
		}
			
		Text title = new Text("Change Variable Values Here");
		Text varName = new Text("Variable Name: "+myVarInfo[0]);
		TextField userInput = new TextField(myVarInfo[1]);
		
		myBox.getChildren().add(title);
		myBox.getChildren().add(varName);
		myBox.getChildren().add(userInput);
		myBox.getChildren().add(ComponentFactory.makeButton("Submit",
				e->{
					//TODO: Handle event here
					String userText = userInput.getText();
					System.out.println(userText);
				}));
	}
	
	public VBox getMyBox(){ return this.myBox; }
}
