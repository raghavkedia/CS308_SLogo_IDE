package frontend.toobar;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;

public class ToolbarFactory {
	
	public static Button makeRunButton(Controller c) {
		return new RunButton(c);
	}

	public static Button makeHelpButton(Controller c) {
		return new HelpButton(c);
	}

	public static ColorPicker makeBGColPicker(Controller c) {
		return new BGColPicker(c);
	}
	
	
	public static Button makeAddImageButton(Controller c) {
		return new AddImageButton(c);
	}
	
	public static Button makeClearButton(Controller c) {
		return new ClearButton(c);
	}
	
	public static Button makeCreateWorkSpaceButton(Controller c) {
		return new CreateWorkSpaceButton(c);
	}
}