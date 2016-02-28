package frontend;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class ComponentFactory {
	public static Button makeButton(String buttonLabel, EventHandler<ActionEvent> buttonAction) {
		Button b = new Button();
		b.setText(buttonLabel);
		b.setOnAction(buttonAction);
		return b;
	}
	
	public static Display makeNewDisplay(double width, double height){
		return new Display(width, height);
	}
	
	public static History makeNewHistory(double width, double height){
		return new History(width, height);
	}
	
	public static Console makeNewConsole(double width, double height){
		return new Console(width, height);
	}
	
	public static Variables makeNewVariables(double width, double height){
		return new Variables(width, height);
	}
	
	public static PopupWindow makeNewPopupWindow(String s){
		return new PopupWindow(s);
	}
	
	public static ToolbarComponent makeNewToolbar(){
		return new ToolbarComponent();
	}
}
