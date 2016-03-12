package frontend;

import controller.Controller;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Subclass of PopupWindow which is used to allow the user to interactively change ColorMap values.
 * @author richardliu
 *
 */

public class ColorMapPopupWindow extends PopupWindow{
	private Controller myController;
	
	public ColorMapPopupWindow(Controller control){
		super();
		myController = control;
		initBox();
	}
	
	/**
	 * Creates the visual VBox for the class.
	 * @param display - The variable/ListView Cell that the user selected.
	 */
	public void initBox(){
		for (int i=0; i<5; i++){
			int index = i;
			Text colorNum = new Text("Index "+i);
			Color c = Color.web(myController.getColorFromMap(index));
			ColorPicker cPicker = new ColorPicker(c);
			cPicker.setOnMouseClicked(
					e -> {
						myController.setColorInMap(c, index);
					});
			myBox.getChildren().add(colorNum);
			myBox.getChildren().add(cPicker);
		}
	}
	
	public VBox getMyBox(){ return this.myBox; }
}