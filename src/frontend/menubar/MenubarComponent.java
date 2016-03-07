package frontend.menubar;

import controller.Controller;
import frontend.VisualComponent;
import javafx.scene.control.MenuBar;


public class MenubarComponent extends  VisualComponent {
	private MenuBar myMenubar;
	private Controller myController;
	
	public MenubarComponent(Controller controller) {
		myMenubar = new MenuBar();
		myController = controller;
		
		myMenubar.getMenus().add(MenubarFactory.makeFileMenu(myController));	
		myMenubar.getMenus().add(MenubarFactory.makeSettingMenu(myController));
		super.setVisual(myMenubar);
	}

}
