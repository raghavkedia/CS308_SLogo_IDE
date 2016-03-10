package frontend.GUI;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileChooserWindow {
	public static File choose(String title, String description, String... extensions) {
		FileChooser fc = new FileChooser();
		fc.setTitle(title);
		fc.getExtensionFilters().setAll(new ExtensionFilter(description, extensions));
		return fc.showOpenDialog(new Stage());	
	}
}
