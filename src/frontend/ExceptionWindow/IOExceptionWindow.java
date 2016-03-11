package frontend.ExceptionWindow;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class IOExceptionWindow {
	public static void display(IOException e) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeaderText(e.getClass().getName());
		alert.setContentText(e.getMessage());

		alert.showAndWait();
	}
}
