package frontend.toobar;

import controller.Controller;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

class BGColPicker extends ColorPicker{
	
	BGColPicker(Controller control) {
		this.setOnAction(
			 e -> {
				 Color c = this.getValue();
				 control.changeDisplayBackgroundColor(c);
			 });
	}
}
