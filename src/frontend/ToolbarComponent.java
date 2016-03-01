package frontend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.geometry.Orientation;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ToolBar;
import javafx.scene.paint.Color;

public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	
	public ToolbarComponent(){
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);
		
		ComboBox<String> colorDropDown = new ComboBox<>();
		String[] colors = new String[]{"Blue", "White", "Grey", "Red", "Green"};
		HashMap<String, Color> colorDict = new HashMap<String, Color>();
		colorDict.put("Blue", Color.BLUE);
		colorDict.put("White", Color.WHITE);
		colorDict.put("Grey", Color.GREY);
		colorDict.put("Red", Color.RED);
		colorDict.put("Green", Color.GREEN);
		colorDropDown.getItems().setAll(Arrays.asList(colors));
		
		colorDropDown.valueProperty().addListener(
				e -> {
					String color = colorDropDown.getSelectionModel().getSelectedItem();
					FrontendManagerAPI.changeDisplayBackgroundColor(colorDict.get(color));
				});
		
		myToolbar.getItems().add(colorDropDown);
		super.setVisual(myToolbar);
	}
}
