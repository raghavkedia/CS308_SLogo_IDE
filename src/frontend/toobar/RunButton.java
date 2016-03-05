package frontend.toobar;

import frontend.FrontendManagerAPI;
import javafx.scene.control.Button;

class RunButton extends Button {
	RunButton() {
		this.setText("run");
		this.setOnAction(
				 e -> {
					 FrontendManagerAPI.executeConsole();
				 });
	}
}
