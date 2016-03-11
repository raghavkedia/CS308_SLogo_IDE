package frontend.toobar;

import controller.Controller;
import frontend.ColorMapPopupWindow;
import frontend.ComponentFactory;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.Button;

public class ColorMapButton extends Button{
	ColorMapButton(Controller control) {
		this.setText("Color Indexes");
		this.setOnAction(
				 e -> {
					 ColorMapPopupWindow pw = new ColorMapPopupWindow(control);
					 ComponentFactory.initNewPopup(pw, 300, 500);
				 });
	}
}
