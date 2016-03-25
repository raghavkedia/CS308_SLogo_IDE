/**
 * This class is part of my code masterpiece.
 * The responsibility of this class is to programmatically determine how to display the component that shows the variables and values.
 * This class also determines how the component responds to user interaction.
 * Variables extends ListVisual, which implements the interface IClickable.
 */

package frontend.listVisual;

import controller.Controller;
import frontend.ComponentFactory;
import frontend.IPopup;
import frontend.PopupWindow;


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
        IPopup popup = ComponentFactory.makeNewVariablesPopupWindow(getMyList().getSelectionModel().getSelectedItem(), getMyController());
        ComponentFactory.initNewPopup(popup, (double) POPUP_HEIGHT, (double) POPUP_WIDTH);
	}
}
