package frontend.toobar;

import controller.Controller;
import frontend.GUI.Init.GUIString;
import frontend.GUI.WebHelp;
import javafx.scene.control.Button;

class HelpButton extends Button {
	HelpButton(Controller control) {
		this.setText(control.getGUIProperty(GUIString.HELP.getKey()));
		this.setOnAction(
				e -> {
					String url = control.getGUIProperty(GUIString.HELP_URL.getKey());
					String title = control.getGUIProperty(GUIString.HELP_TITLE.getKey());
					new WebHelp(url, title);
				});
	}
}

