package frontend.toobar;

import java.io.File;
import backend.data.Character;
import controller.Controller;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import frontend.Portrait;
import frontend.GUI.Init.GUIString;

class AddImageButton extends Button {
	AddImageButton(Controller control) {
		this.setText(control.getGUIProperty(GUIString.PORTRAIT.getKey()));
		this.setOnAction(
				e ->{
					FileChooser fc = new FileChooser();
					fc.setTitle(control.getGUIProperty(GUIString.PORTRAIT_TITLE.getKey()));
					fc.getExtensionFilters().setAll(new ExtensionFilter("Image Files (.png)", "*.png"));
					File imgFile = fc.showOpenDialog(control.getMyStage());
					if (imgFile != null) {
						Character c = new Character();
						c.setName(Integer.toString(Portrait.getAndIncrementId()));
//						c.setImage(new Image("file://"+imgFile.toString()));
						c.setImagePath("file://"+imgFile.toString());
						c.setVisability(true);
						c.setPenState(true);
						c.setPenColor("000000");
						control.addNewChar(c);
					}
					
				});
	}
}