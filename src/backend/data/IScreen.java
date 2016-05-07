package backend.data;

import java.util.ResourceBundle;

public interface IScreen {
	
	public double[] translateCoords(double x, double y, ResourceBundle myDimensionResources);
	
}
