package frontend.menubar;

import controller.Controller;
import frontend.VisualComponent;
import javafx.scene.control.MenuBar;


public class MenubarComponent extends  VisualComponent {
	private MenuBar myMenubar;
	private Controller myController;
	
	public MenubarComponent(Controller controller) {
		myMenubar = new MenuBar();
		
		myMenubar.getMenus().add(MenubarFactory.makeFileMenu(myController));	
		
		super.setVisual(myMenubar);
	}

}
