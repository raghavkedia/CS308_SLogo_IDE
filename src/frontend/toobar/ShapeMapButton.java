package frontend.toobar;

import controller.Controller;
import frontend.ComponentFactory;
import frontend.popUp.ColorMapPopupWindow;
import frontend.popUp.ShapeMapPopupWindow;
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
