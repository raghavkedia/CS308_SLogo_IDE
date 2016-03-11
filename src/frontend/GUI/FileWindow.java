package frontend.GUI;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * File saver window
 * 
 * @author Jiangzhen Yu
 */
public class FileWindow {
	
	/**
	 * static method 
	 * 
     * @param title: title of the popup stage window
     * @param description: extension filter description
     * @param extesions: extensions to be filtered in FileChooser
	 */
	public static File save(String title, String description, String... extensions) {
        FileChooser fileChooser = makeFileChooser(title, description, extensions);
		return fileChooser.showSaveDialog(new Stage());
	}
	
	public static File choose(String title, String description, String... extensions) {
		FileChooser fileChooser = makeFileChooser(title, description, extensions);
		return fileChooser.showOpenDialog(new Stage());	
	}
	
	private static FileChooser makeFileChooser(String title, String description, String... extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter(description, extensions));
        return fileChooser;
	}
}
