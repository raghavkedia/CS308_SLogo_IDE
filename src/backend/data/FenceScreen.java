package backend.data;

import java.util.ResourceBundle;

public class FenceScreen implements IScreen{

	@Override
	public double[] translateCoords(double x, double y, ResourceBundle myDimensionResources) {
		// TODO Auto-generated method stub
		double[] newCoord = new double[2];
		newCoord[0] = Math.signum(x) * Math.min(Math.abs(x), Double.parseDouble(myDimensionResources.getString("x_axis")));
		newCoord[1] = Math.signum(y) * Math.min(Math.abs(y), Double.parseDouble(myDimensionResources.getString("y_axis")));
		return newCoord;
	}
	
	
	
}
