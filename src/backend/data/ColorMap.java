package backend.data;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import exceptions.InvalidIndexColorError;


public class ColorMap {
	
	private Map<Integer, String> myColorMap;
	private ResourceBundle myErrorResources;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public ColorMap() {
		// TODO Auto-generated constructor stub
		myColorMap = new HashMap<Integer, String>();
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
	}
	
	public void addColor(int index, double r, double g, double b){
		Color color = new Color((float) r, (float) g, (float) b);
		String hex = Integer.toHexString( color.getRGB() & 0x00ffffff );
		myColorMap.put(index, hex);
	}
	
	public void addColor(int index, String color){
		myColorMap.put(index, color);
	}
	
	public boolean indexExists(int index){
		if(myColorMap.get(index) == null){
			return false;
		}
		return true;
	}
	
	public String getColor(int index) {
		return myColorMap.get(index);
	}

}
