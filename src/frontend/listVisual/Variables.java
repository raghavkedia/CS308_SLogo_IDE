package frontend.listVisual;

import controller.Controller;
import frontend.ComponentFactory;
import frontend.popUp.PopupWindow;


public class Variables extends ListVisual {
    public static final int POPUP_HEIGHT = 300;
    public static final int POPUP_WIDTH = 200;
	public Variables(double width, double height, Controller control){
		super(width, height, control);
	}

	/**
	 * On a double click, create a PopupWindow to change variables.
	 */
	@Override
	public void respondToClick() {
        PopupWindow popup = ComponentFactory.makeNewVariablesPopupWindow(getMyList().getSelectionModel().getSelectedItem(), getMyController());
        ComponentFactory.initNewPopup(popup, POPUP_HEIGHT, POPUP_WIDTH);
	}
}
