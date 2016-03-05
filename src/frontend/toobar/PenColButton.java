package frontend.toobar;

import frontend.FrontendManagerAPI;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;

class PenColButton extends ColorPicker {
	PenColButton() {
		this.setOnAction(
			 e -> {
				 Color c = this.getValue();
				 FrontendManagerAPI.changeLineColor(c);
			 });
	}
}
