package frontend.toobar;

import controller.Controller;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

class PenColPicker extends ColorPicker {
	PenColPicker(Controller control) {
		this.setOnAction(
			 e -> {
				 Color c = this.getValue();
				 control.changeLineColor(c);
			 });
	}
}
