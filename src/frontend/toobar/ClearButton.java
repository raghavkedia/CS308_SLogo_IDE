package frontend.toobar;

import controller.Controller;
import javafx.scene.control.Button;

class ClearButton extends Button{
	ClearButton(Controller control) {
//		String buttonLabel = FrontendManagerAPI.getGUIProperty("help_Name");
		this.setText("clear");
		this.setOnAction(
				 e -> {
					 control.clearConsole();
				 });
	}
}
