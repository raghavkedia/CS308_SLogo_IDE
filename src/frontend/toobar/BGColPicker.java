package frontend.toobar;

import frontend.FrontendManagerAPI;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

class BGColPicker extends ColorPicker{
	
	BGColPicker() {
		this.setOnAction(
			 e -> {
				 Color c = this.getValue();
				 FrontendManagerAPI.changeDisplayBackgroundColor(c);
			 });
	}
}
