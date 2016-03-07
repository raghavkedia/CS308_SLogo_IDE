package frontend.toobar;

import controller.Controller;
import frontend.ComponentFactory;
import frontend.PenPropertiesPopupWindow;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PenPropertiesButton extends Button{
	PenPropertiesButton(Controller control){
		this.setText("change pen properties");
		this.setOnAction(
				e ->{
					
			        PenPropertiesPopupWindow popup = ComponentFactory.makeNewPenPropertiesPopupWindow(control);
			        ComponentFactory.initNewPopup(popup, 300, 400);
				});
	}
}
