package frontend;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * TODO: Refactor this class into a general abstract class. Make this a Variables-specific abstract class.
 * @author richardliu
 *
 */
public class PopupWindow extends VisualComponent{
	private VBox myBox;
	
	public PopupWindow(String myDisplay){
		myBox = new VBox(20);
		myBox.setAlignment(Pos.CENTER);
		initBox(myDisplay);
		super.setVisual(myBox);
		super.setColor(Color.GREY);
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
