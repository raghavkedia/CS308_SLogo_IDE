package frontend;

abstract class VisualComponent {
	private double myX;
	private double myY;
	private double myWidth;
	private double myHeight;
	
	public void bind(double xCor, double yCor){
		this.myX = xCor;
		this.myY = yCor;
	}
	
	public double getMyX() {return myX;}
	public void setMyX(double myX) {this.myX = myX;}
	public double getMyY() {return myY;}
	public void setMyY(double myY) {this.myY = myY;}
	public double getMyWidth() {return myWidth;}
	public void setMyWidth(double myWidth) {this.myWidth = myWidth;}
	public double getMyHeight() {return myHeight;}
	public void setMyHeight(double myHeight) {this.myHeight = myHeight;}
}
