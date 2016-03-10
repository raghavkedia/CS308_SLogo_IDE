package frontend.menubar;

import controller.Controller;
import frontend.SettingSaver;
import javafx.scene.control.MenuItem;

public class SettingSaverMenuItem extends MenuItem {

	public SettingSaverMenuItem(Controller controller) {
		this.setText("save current setting");
		this.setOnAction(
				e ->{
					new SettingSaver(controller.getFrontendManager(), "default");
				});
	}
}
