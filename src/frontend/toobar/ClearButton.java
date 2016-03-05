package frontend.toobar;

import frontend.FrontendManagerAPI;
import javafx.scene.control.Button;

class ClearButton extends Button{
	ClearButton() {
//		String buttonLabel = FrontendManagerAPI.getGUIProperty("help_Name");
		this.setText("clear");
		this.setOnAction(
				 e -> {
					 FrontendManagerAPI.clearConsole();
				 });
	}
}
