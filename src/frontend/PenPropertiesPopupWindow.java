package frontend;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PenPropertiesPopupWindow extends PopupWindow{
	private Controller myController;
	
	public PenPropertiesPopupWindow(Controller control){
		super();
		initBox();
		myController = control;
	}
	
	void initBox() {
		Text penColorLabel = new Text("Change Pen Color");
		ColorPicker changePenColor = new ColorPicker();
		
		Text penThicknessLabel = new Text("Change Pen Thickness");
		TextField changePenThickness = new TextField(); //get current thickness
		
		Text penPatternLabel = new Text("Change Pen Pattern");
		ComboBox<String> changePenPattern = new ComboBox<String>();
		changePenPattern.getItems().addAll("Dashed", "Dotted", "Solid");
		
		Button submitAll = ComponentFactory.makeButton("Submit", 
				e -> {
					if (changePenColor.getValue() != null){
						myController.changeLineColor(changePenColor.getValue());
					}
					if (changePenThickness.getText() != null){
						//set pen thickness
					}
					if (changePenPattern.getValue() != null){
						switch(changePenPattern.getValue()){
							
						}
					}
				});
		
		myBox.getChildren().add(penColorLabel);
		myBox.getChildren().add(changePenColor);
		myBox.getChildren().add(penThicknessLabel);
		myBox.getChildren().add(changePenThickness);
		myBox.getChildren().add(penPatternLabel);
		myBox.getChildren().add(changePenPattern);
		myBox.getChildren().add(submitAll);
	}

}
