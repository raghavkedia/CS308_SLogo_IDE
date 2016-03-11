package frontend.menubar;

import java.io.File;

import controller.Controller;
import frontend.GUI.FileSaverWindow;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import util.FileHandler;

public class FileSaverMenuItem extends MenuItem {

	public FileSaverMenuItem(Controller controller) {
		this.setText("save file");
        this.setOnAction(
				e ->{
					String content = controller.getConsoleText();
					File file = FileSaverWindow.save("", "Slogo Files", "*.logo");
		              if(file != null){
		                  FileHandler.saveFile(content, file);
		              }

				}
        );
	}

}
