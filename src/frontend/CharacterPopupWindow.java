package frontend;

import controller.Controller;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CharacterPopupWindow extends PopupWindow {
	private Controller myController;
	
	public CharacterPopupWindow(String charId, Controller control){
		super();
		myController = control;
		initBox(charId);
	}
	
	/**
	 * Creates the visual VBox for the class.
	 * @param display - The variable/ListView Cell that the user selected.
	 */
	public void initBox(String charId){
		//Pen color, visibility, pen up/down, pen width, change image

		boolean isActive = myController.isCharIdActive(charId);
		boolean isVisible = myController.isCharIdVisible(charId);
		boolean isPenDown = myController.isCharIdPenDown(charId);
		
		Text activeness = new Text();
		String activenessText = isActive? "Active: Yes":"Active: No";
		activeness.setText(activenessText);
		
		Text setActive = new Text("Set Active/Not Active Below");
		ComboBox<String> changeActiveness = new ComboBox<String>();
		changeActiveness.getItems().addAll("Active", "Not Active");
		if (isActive) changeActiveness.getSelectionModel().select("Active");
		else changeActiveness.getSelectionModel().select("Not Active");
		
		Text visibility = new Text();
		String visibilityText = isVisible? "Visible: Yes":"Visible: No";
		visibility.setText(visibilityText);
		
		Text setVisible = new Text("Set Visible/Not Visible Below");
		ComboBox<String> changeVisibility = new ComboBox<String>();
		changeVisibility.getItems().addAll("Visible", "Not Visible");
		if (isVisible) changeVisibility.getSelectionModel().select("Visible");
		else changeVisibility.getSelectionModel().select("Not Visible");
		
		Text penDown= new Text();
		String penDownText = isPenDown? "Pen Down":"Pen Up";
		penDown.setText(penDownText);
		
		Text setPenState = new Text("Set Pen Up/Down Below");
		ComboBox<String> changePenState = new ComboBox<String>();
		changePenState.getItems().addAll("Pen Up", "Pen Down");
		if (isPenDown) changePenState.getSelectionModel().select("Pen Down");
		else changePenState.getSelectionModel().select("Pen Up");
		
		myBox.getChildren().add(activeness); 
		myBox.getChildren().add(setActive); 
		myBox.getChildren().add(changeActiveness);
		myBox.getChildren().add(visibility); 
		myBox.getChildren().add(setVisible); 
		myBox.getChildren().add(changeVisibility);
		myBox.getChildren().add(penDown);
		myBox.getChildren().add(setPenState); 
		myBox.getChildren().add(changePenState);
		myBox.getChildren().add(ComponentFactory.makeButton("Submit", 
				e -> {
						boolean newActive = changeActiveness.getSelectionModel().getSelectedItem().equals("Not Active")? false:true;
						boolean newVisible = changeVisibility.getSelectionModel().getSelectedItem().equals("Not Visible")? false:true;
						boolean newPenDown = changePenState.getSelectionModel().getSelectedItem().equals("Pen Up")? false:true;
					myController.setCharIdActive(charId, newActive);
					myController.setCharIdVisible(charId, newVisible);
					myController.setCharIdPenDown(charId, newPenDown);
				}));
	}
	
	public VBox getMyBox(){ return this.myBox; }
}