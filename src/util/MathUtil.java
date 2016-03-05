package util;

public class MathUtil {

	public static double findDistance(double x1, double x2, double y1, double y2) {
		double deltaX = x1 - x2; 
		double deltaY = y1 - y2;
		return Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
	}
	
	public static double convertDegrees(double angle) {
		return (Math.PI * angle / 180) % 360;
	}

}
