package backend.data;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.scene.image.Image;

public class ShapeMap {

	private Map<Integer, String> myShapeMap;
	private ResourceBundle myErrorResources;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	
	public ShapeMap() {
		// TODO Auto-generated constructor stub
		myShapeMap = new HashMap<Integer, String>();
		myErrorResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "ErrorMessages");
	}
	
	public void addShape(int index, String imagePath){
		myShapeMap.put(index, imagePath);
	}
	
//	public void addShape(int index, double r, double g, double b){
//		Color color = new Color((float) r, (float) g, (float) b);
//		String hex = Integer.toHexString( color.getRGB() & 0x00ffffff );
//		myColorMap.put(index, hex);
//	}
	
	public boolean indexExists(int index){
		if(myShapeMap.get(index) == null){
			return false;
		}
		return true;
	}
	
	public String getImagePath(int index) {
		return myShapeMap.get(index);
	}

}
