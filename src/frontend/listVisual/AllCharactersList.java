package frontend.listVisual;

import controller.Controller;
import frontend.CharacterPopupWindow;
import frontend.ComponentFactory;
import java.util.HashMap;
import java.util.Map;

import backend.data.Character;

public class AllCharactersList extends ListVisual {
    private Map<Integer, String> myIDs;
    public static final String ID = "myID: ";
    public static final String X = ", x: ";
    public static final String Y = ", y: ";
    public static final String ANGLE = ", angle: ";
    
    
	public AllCharactersList(double width, double height, Controller controller) {
		super(width, height, controller);
		myIDs = new HashMap<Integer, String>();
	}

    public void addToData(Character c) {
    	myIDs.put(size(), c.getName());
    	String id = ID + c.getName();
    	String xcord = X +  c.getCoordX();
    	String ycord = Y +  c.getCoordY();
    	String angle = ANGLE +  c.getMyAngle();
    	addToData(id + xcord + ycord + angle);
    }
    
	/**
	 * On a double click, create a PopupWindow to change variables.
	 */
	@Override
	public void respondToClick() {
		int index = getMyList().getSelectionModel().getSelectedIndex();
		CharacterPopupWindow popup = ComponentFactory.makeNewCharactersPopupWindow(myIDs.get(index),
																				   getMyController());
		ComponentFactory.initNewPopup(popup, 300, 700);
	}
}
