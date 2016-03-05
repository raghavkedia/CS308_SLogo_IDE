package frontend;

import java.util.Properties;

import controller.Controller;
import frontend.GUI.WebHelp;
import frontend.toobar.ToolbarComponent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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

	public static ColorPicker makeNewColorPicker(Controller control){
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setOnAction(
			 e -> {
				 Color c = colorPicker.getValue();
				 control.changeDisplayBackgroundColor(c);
			 });
		return colorPicker;
	}
	
	public static ColorPicker makeNewColorPickerLine(Controller control){
		ColorPicker colorPicker = new ColorPicker();
		colorPicker.setOnAction(
			 e -> {
				 Color c = colorPicker.getValue();
				 control.changeLineColor(c);
			 });
		return colorPicker;
	}
	
	public static Button makeButton(String buttonLabel, Controller c) {
		Button b = new Button();
		b.setText(buttonLabel);
		switch(buttonLabel) {
		case "CLEAR":
			b.setOnAction(
					 e -> {
						 c.clearConsole();
					 });
			break;
		case "RUN":
			b.setOnAction(
					 e -> {
						 c.executeConsole();
					 });
			break;
		case "HELP":
			b.setOnAction(
					e -> {
						String url = c.getGUIProperty("help_url");
						String title = c.getGUIProperty("help_title");
						WebHelp webHelp = new WebHelp(url, title);
						try {
							webHelp.start(new Stage());
						} catch (Exception exception) {
							exception.printStackTrace();
						}
					});
			break;			
		}
		return b;
	}
}
