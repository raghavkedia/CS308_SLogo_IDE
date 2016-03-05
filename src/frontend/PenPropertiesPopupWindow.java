package frontend;

import controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import backend.data.Data.PenPattern;

public class PenPropertiesPopupWindow extends PopupWindow{
	private Controller myController;
	
	public PenPropertiesPopupWindow(Controller control){
		super();
		myController = control;
		initBox();
	}
	
	void initBox() {
		Text penColorLabel = new Text("Change Pen Color");
		ColorPicker changePenColor = new ColorPicker(myController.getPenColor());
		
		Text penThicknessLabel = new Text("Change Pen Thickness");
		TextField changePenThickness = new TextField(""+myController.getLineThickness()); //get current thickness
		
		Text penPatternLabel = new Text("Change Pen Pattern");
		ComboBox<String> changePenPattern = new ComboBox<String>();
		changePenPattern.getItems().addAll("Dashed", "Dotted", "Solid");
		
		Button submitAll = ComponentFactory.makeButton("Submit", 
				e -> {
					if (changePenColor.getValue() != null){
						myController.changeLineColor(changePenColor.getValue());
					}
					if (changePenThickness.getText() != null){
						myController.setLineThickness(Double.parseDouble(changePenThickness.getText()));
					}
					if (changePenPattern.getValue() != null){
						switch(changePenPattern.getValue()){
							case "Solid":
								myController.setPenPattern(PenPattern.SOLID);
								break;
							case "Dashed":
								myController.setPenPattern(PenPattern.DASHED);
								break;
							case "Dotted":
								myController.setPenPattern(PenPattern.DOTTED);
								break;
							default:
								myController.setPenPattern(PenPattern.SOLID);	
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
