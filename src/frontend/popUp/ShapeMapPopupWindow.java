// This entire file is part of my masterpiece.
// Jiangzhen Yu

package frontend.popUp;

import java.io.File;

import backend.data.Character;
import controller.Controller;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * Subclass of PopupWindow which is used to allow the user to interactively change ShapeMap values.
 * @author richardliu
 *
 */

public class ShapeMapPopupWindow extends PopupWindow{
	private Controller myController;
	
	public ShapeMapPopupWindow(Controller control){
		super();
		myController = control;
		initBox();
	}
	
	/**
	 * Creates the visual VBox for the class.
	 * @param display - The variable/ListView Cell that the user selected.
	 */
	public void initBox(){
		loadImages();
	}
	
	public void loadImages(){
		myBox.getChildren().clear();
		for (int i=0; i<5; i++){
			int index = i;
			Text shapeNum = new Text("Index "+i);
			ImageView img = new ImageView(myController.getImagePathFromMap(index));
			img.setFitWidth(50);
			img.setFitHeight(50);
			img.setOnMouseClicked(
					e -> {
						FileChooser fc = new FileChooser();
						fc.getExtensionFilters().setAll(new ExtensionFilter("Image Files (.png)", "*.png"));
						File imgFile = fc.showOpenDialog(myController.getMyStage());
						if (imgFile != null) {
							myController.setImagePathInMap("file://"+imgFile.toString(), index);
						}
						loadImages();
					});
			
			myBox.getChildren().add(shapeNum);
			myBox.getChildren().add(img);
		}
	}
	
	public VBox getMyBox(){ return this.myBox; }
}