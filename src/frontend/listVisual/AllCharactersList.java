package frontend.listVisual;

import controller.Controller;
import exceptions.InvalidCharacterError;
import frontend.CharacterPopupWindow;
import frontend.ComponentFactory;
import frontend.IPopup;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import backend.data.Character;

public class AllCharactersList extends ListVisual {
    private Map<Integer, Character> myCharacters;
    public static final String ID = "myID: ";
    public static final String X = ", x: ";
    public static final String Y = ", y: ";
    public static final String ANGLE = ", angle: ";
    
    
	public AllCharactersList(double width, double height, Controller controller) {
		super(width, height, controller);
		myCharacters = new HashMap<Integer, Character>();
	}

    public void addToData(Character c) {
    	myCharacters.put(size(), c);
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
		IPopup popup;
		try {
			popup = ComponentFactory.makeNewCharactersPopupWindow(myCharacters.get(index).getName(),
																					   getMyController());

			ComponentFactory.initNewPopup(popup, 300, 700);
		} catch (InvalidCharacterError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<Integer, Character> getCharMap() {
		return Collections.unmodifiableMap(myCharacters);
	}
}
