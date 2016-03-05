package frontend.toobar;

import frontend.FrontendManagerAPI;
import frontend.GUI.WebHelp;
import javafx.scene.control.Button;
import javafx.stage.Stage;

class HelpButton extends Button {
	HelpButton() {
//		String buttonLabel = FrontendManagerAPI.getGUIProperty("help_Name");
		this.setText("help");
		this.setOnAction(
				e -> {
					String url = FrontendManagerAPI.getGUIProperty("help_url");
					String title = FrontendManagerAPI.getGUIProperty("help_title");
					WebHelp webHelp = new WebHelp(url, title);
					try {
						webHelp.start(new Stage());
					} catch (Exception exception) {
						exception.printStackTrace();
					}
				});
	}
}

