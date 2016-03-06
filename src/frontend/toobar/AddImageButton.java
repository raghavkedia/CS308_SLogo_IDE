package frontend.toobar;

import java.io.File;
import backend.data.Character;
import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

class AddImageButton extends Button {
	AddImageButton(Controller control) {
		this.setText("add_image");
		this.setOnAction(
				e ->{
					FileChooser fc = new FileChooser();
					fc.setTitle("Select an Image");
					fc.getExtensionFilters().setAll(new ExtensionFilter("Image Files (.png)", "*.png"));
					File imgFile = fc.showOpenDialog(control.getMyStage());
					if (imgFile != null) {
						Character c = new Character();
						c.setName(Integer.toString(c.hashCode()));
						c.setImage(new Image("file://"+imgFile.toString()));
						control.addNewChar(c);
					}
					
				});
	}
}
