package frontend.menubar;

import controller.Controller;
import javafx.scene.control.Menu;

public class MenubarFactory {

	
	public static Menu makeFileMenu(Controller c) {
		return new FileMenu(c);
	}

}
