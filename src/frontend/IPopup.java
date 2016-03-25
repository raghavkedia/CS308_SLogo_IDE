package frontend;

import exceptions.InvalidCharacterError;
import javafx.scene.layout.VBox;

public interface IPopup {
	public void initBox(String args) throws InvalidCharacterError;
	public VBox getMyBox();
}
