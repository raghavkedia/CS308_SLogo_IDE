package frontend;

import java.util.Properties;

import controller.Controller;
import frontend.toobar.ToolbarComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ComponentFactory {
	public static Button makeButton(String buttonLabel, EventHandler<ActionEvent> buttonAction) {
		Button b = new Button();
		b.setText(buttonLabel);
		b.setOnAction(buttonAction);
		return b;
	}
	
	public static Display makeNewDisplay(double width, double height, Controller control){
		return new Display(width, height, control);
	}
	
	public static History makeNewHistory(double width, double height, Controller control){
		return new History(width, height, control);
	}
	
	public static Console makeNewConsole(double width, double height, Controller control){
		return new Console(width, height, control);
	}
	
	public static Variables makeNewVariables(double width, double height, Controller control){
		return new Variables(width, height, control);
	}
	
	public static VariablesPopupWindow makeNewVariablesPopupWindow(String s, Controller control){
		return new VariablesPopupWindow(s, control);
	}
	
	public static PenPropertiesPopupWindow makeNewPenPropertiesPopupWindow(Controller control){
		return new PenPropertiesPopupWindow(control);
	}
	
	public static ToolbarComponent makeNewToolbar(Properties GUIProp, Controller control){
		return new ToolbarComponent(GUIProp, control);
	}
	
	public static void makeNewPopupWindow(PopupWindow pw){
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(pw.getMyBox(), 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	public static void makeNewPopupWindow(PopupWindow pw, double width, double height){
		Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        Scene dialogScene = new Scene(pw.getMyBox(), width, height);
        dialog.setScene(dialogScene);
        dialog.show();
	}

}
