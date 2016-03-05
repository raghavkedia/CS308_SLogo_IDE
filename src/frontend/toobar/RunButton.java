package frontend.toobar;

import controller.Controller;
import javafx.scene.control.Button;

class RunButton extends Button {
	RunButton(Controller control) {
		this.setText("run");
		this.setOnAction(
				 e -> {
					 control.executeConsole();
				 });
	}
}
