package frontend;

import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Subclass of PopupWindow which is used to allow the user to interactively change variable values.
 * @author richardliu
 *
 */

public class VariablesPopupWindow extends PopupWindow {
	
	public VariablesPopupWindow(String myDisplay){
		super();
		initBox(myDisplay);
	}
	
	/**
	 * Creates the visual VBox for the class.
	 * @param display - The variable/ListView Cell that the user selected.
	 */
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
					FrontendManagerAPI.updateVariableValue(myVarInfo[0].substring(1), userText);
				}));
	}
	
	public VBox getMyBox(){ return this.myBox; }
}
