package frontend.toobar;

import controller.Controller;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.Button;

class ClearButton extends Button{
	ClearButton(Controller control) {
		this.setText(control.getGUIProperty(GUIString.CLEAR.getKey()));
		this.setOnAction(
				 e -> {
					 control.clearConsole();
				 });
	}
}
