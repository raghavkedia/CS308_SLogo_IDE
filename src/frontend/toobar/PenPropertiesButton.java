package frontend.toobar;

import controller.Controller;
import frontend.ComponentFactory;
import frontend.PenPropertiesPopupWindow;
import javafx.scene.control.Button;
import frontend.GUI.Init.GUIString;

public class PenPropertiesButton extends Button{
	PenPropertiesButton(Controller control){
		this.setText(control.getGUIProperty(GUIString.PEN.getKey()));
		this.setOnAction(
				e ->{
			        PenPropertiesPopupWindow popup = ComponentFactory.makeNewPenPropertiesPopupWindow(control);
			        ComponentFactory.initNewPopup(popup, 300, 400);
				});
	}
}
