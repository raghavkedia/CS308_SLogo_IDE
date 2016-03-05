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
					Stage dialog = new Stage();
			        dialog.initModality(Modality.APPLICATION_MODAL);
			        PenPropertiesPopupWindow popup = ComponentFactory.makeNewPenPropertiesPopupWindow(control);
			        Scene dialogScene = new Scene(popup.getMyBox(), 300, 400);
			        dialog.setScene(dialogScene);
			        dialog.show();
				});
	}
}
