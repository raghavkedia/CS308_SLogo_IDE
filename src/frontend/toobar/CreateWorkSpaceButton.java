package frontend.toobar;

import controller.Controller;
import javafx.scene.control.Button;

class CreateWorkSpaceButton extends Button {
	CreateWorkSpaceButton (Controller control) {
	this.setText("createWS");
	this.setOnAction(
			 e -> {
				control.createWorkSpace();
			 });
	}
}