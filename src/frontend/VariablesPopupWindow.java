/**
 * This class is part of my code masterpiece.
 * The purpose of this class is to provide a means for the user to interact with the variables on the backend.
 * For instance, if the user wants to adjust a value through the IDE, s/he can do so through this popup window.
 * Although the magic values are meh, I'm happy I was able to write clean code including lambda expressions to simplify the actions that this class takes.
 * Lastly, I am glad that this class implements the IPopup interface, which makes it then easier to pass around.
 */

package frontend;

import controller.Controller;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * Subclass of PopupWindow which is used to allow the user to interactively change variable values.
 * @author richardliu
 *
 */

public class VariablesPopupWindow extends PopupWindow {
	private Controller myController;
	
	public static String EQUALS = "=";
	
	public VariablesPopupWindow(String myDisplay, Controller control){
		super();
		myController = control;
		initBox(myDisplay);
	}
	
	/**
	 * Creates the visual VBox for the class.
	 * @param display - The variable/ListView Cell that the user selected.
	 */
	public void initBox(String display){
		String[] myVarInfo = display.split(EQUALS);
		if (myVarInfo.length != 2) {
			throw new RuntimeException();
		}
			
		Text title = new Text("Change Variable Values Here");
		Text varName = new Text("Variable Name: "+myVarInfo[0]);
		TextField userInput = new TextField(myVarInfo[1]);
		
		myBox.getChildren().add(title);
		myBox.getChildren().add(varName);
		myBox.getChildren().add(userInput);
		myBox.getChildren().add(ComponentFactory.makeButton("Submit",
				e->{
					String userText = userInput.getText();
					myController.updateVariableValue(myVarInfo[0].substring(1), userText);
				}));
	}
}
