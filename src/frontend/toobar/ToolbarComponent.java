package frontend.toobar;
import controller.Controller;
import frontend.VisualComponent;
import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;


public class ToolbarComponent extends VisualComponent{
	private ToolBar myToolbar;
	private Controller myController;
	
	public ToolbarComponent(Controller control) {
		myController = control;
		myToolbar = new ToolBar();
		myToolbar.setOrientation(Orientation.HORIZONTAL);

		myToolbar.getItems().add(ToolbarFactory.makeBGColPicker(myController));		
		myToolbar.getItems().add(ToolbarFactory.makeAddImageButton(myController)); 	
		myToolbar.getItems().add(ToolbarFactory.makeRunButton(myController));
		myToolbar.getItems().add(ToolbarFactory.makeClearButton(myController));
		myToolbar.getItems().add(ToolbarFactory.makeHelpButton(myController));
		myToolbar.getItems().add(ToolbarFactory.makeCreateWorkSpaceButton(myController));
		
		super.setVisual(myToolbar);
	}
}