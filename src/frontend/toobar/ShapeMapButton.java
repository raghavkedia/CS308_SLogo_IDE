package frontend.toobar;

import controller.Controller;
import frontend.ColorMapPopupWindow;
import frontend.ComponentFactory;
import frontend.ShapeMapPopupWindow;
import javafx.scene.control.Button;

public class ShapeMapButton extends Button{
	ShapeMapButton(Controller control) {
		this.setText("Image Indexes");
		this.setOnAction(
				 e -> {
					 ShapeMapPopupWindow pw = new ShapeMapPopupWindow(control);
					 ComponentFactory.initNewPopup(pw, 300, 600);
				 });
	}
}
