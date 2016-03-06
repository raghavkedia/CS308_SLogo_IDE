package backend.data;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import exceptions.InvalidIndexColorError;

import java.awt.Color;

public class ColorMap {
	
	private Map<Integer, Color> myColorMap;
	private ResourceBundle myErrorResources;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public ColorMap() {
		// TODO Auto-generated constructor stub
		myColorMap = new HashMap<Integer, Color>();
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
	}
	
	public void addColor(int index, float r, float g, float b){
		Color color = new Color(r, g, b);
		myColorMap.put(index, color);
	}
	
	public Color getColor(int index) throws InvalidIndexColorError{
		if(myColorMap.get(index).equals(null)){
			throw new InvalidIndexColorError(myErrorResources.getString("InvalidIndexColorError"));
		}
		return myColorMap.get(index);
	}

}
