package frontend.menubar;

import java.io.File;

import controller.Controller;
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

		            FileChooser fileChooser = new FileChooser();
		            fileChooser.getExtensionFilters().addAll(
		                    new ExtensionFilter("Slogo Files", "*.logo"));
					File file = fileChooser.showSaveDialog(new Stage());
		              if(file != null){
		                  FileHandler.saveFile(content, file);
		              }

				}
        );
	}

}
