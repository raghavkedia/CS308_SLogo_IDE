package frontend.toobar;

import controller.Controller;
import frontend.GUI.Init.GUIString;
import frontend.GUI.WebHelp;
import javafx.scene.control.Button;
import javafx.stage.Stage;

class HelpButton extends Button {
	HelpButton(Controller control) {
		this.setText(control.getGUIProperty(GUIString.HELP.getKey()));
		this.setOnAction(
				e -> {
					String url = control.getGUIProperty("help_url");
					String title = control.getGUIProperty("help_title");
					WebHelp webHelp = new WebHelp(url, title);
					try {
						webHelp.start(new Stage());
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				});
	}
}

