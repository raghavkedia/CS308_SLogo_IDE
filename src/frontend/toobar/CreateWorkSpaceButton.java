package frontend.toobar;

import controller.Controller;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.Button;

class CreateWorkSpaceButton extends Button {
	CreateWorkSpaceButton (Controller control) {
	this.setText(control.getGUIProperty(GUIString.WORKSPACE.getKey()));
	this.setOnAction(
			 e -> {
				control.createWorkSpace();
			 });
	}
}