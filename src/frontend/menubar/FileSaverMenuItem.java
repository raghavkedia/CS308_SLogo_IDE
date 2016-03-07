package frontend.menubar;

import java.io.File;

import controller.Controller;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import util.FileHandler;

public class FileSaverMenuItem extends MenuItem {

	public FileSaverMenuItem(Controller controller) {
		this.setText("save file");
        this.setOnAction(
				e ->{
					String content = controller.getConsoleText();
					try {
			            FileChooser fileChooser = new FileChooser();
			            fileChooser.getExtensionFilters().addAll(
			                    new ExtensionFilter("Slogo Files", "*.logo"));
						File file = fileChooser.showSaveDialog(controller.getMyStage());
			              if(file != null){
			                  FileHandler.saveFile(content, file);
			              }
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
        		);
	}

}
