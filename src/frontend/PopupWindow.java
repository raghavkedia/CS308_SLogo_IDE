package frontend;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Wrapper class for a VBox -- which defines the contents of a PopupWindow.
 * @author richardliu
 *
 */
public abstract class PopupWindow extends VisualComponent{
	protected VBox myBox;
	
	public PopupWindow(){
		myBox = new VBox(20);
		myBox.setAlignment(Pos.CENTER);
		super.setVisual(myBox);
		super.setColor(Color.GREY);
	}
	
	public VBox getMyBox(){ return this.myBox; }
}
