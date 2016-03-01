package frontend;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * @author richardliu
 *
 */
abstract class PopupWindow extends VisualComponent{
	protected VBox myBox;
	
	public PopupWindow(){
		myBox = new VBox(20);
		myBox.setAlignment(Pos.CENTER);
		super.setVisual(myBox);
		super.setColor(Color.GREY);
	}
	
	abstract void initBox(String display);
	
	public VBox getMyBox(){ return this.myBox; }
}
