package backend.data;

import java.util.ResourceBundle;

public class WindowScreen implements IScreen{

	@Override
	public double[] translateCoords(double x, double y, ResourceBundle myDimensionResources) {
		// TODO Auto-generated method stub
		double[] newCoord = new double[2];
		newCoord[0] = x;
		newCoord[1] = y;
		return newCoord;
	}

}
