package frontend.menubar;

import java.io.File;

import controller.Controller;
import frontend.GUI.FileWindow;
import frontend.GUI.Init.GUIString;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import util.FileHandler;

public class FileSaverMenuItem extends MenuItem {

	public FileSaverMenuItem(Controller controller) {
		this.setText(controller.getGUIProperty(GUIString.FILE_MENU_SAVE.getKey()));
        this.setOnAction(
				e ->{
					String title = controller.getGUIProperty(GUIString.FILE_SAVE_TITLE.getKey());
					String content = controller.getConsoleText();
					File file = FileWindow.save(title, "Slogo Files", "*.logo");
		              if(file != null){
		                  FileHandler.saveFile(content, file);
		              }

				}
        );
	}

}
