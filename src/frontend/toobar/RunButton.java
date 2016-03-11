package frontend.toobar;

import controller.Controller;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.Button;

class RunButton extends Button {
	RunButton(Controller control) {
		this.setText(control.getGUIProperty(GUIString.RUN.getKey()));
		this.setOnAction(
				 e -> {
					 control.executeConsole();
				 });
	}
}
