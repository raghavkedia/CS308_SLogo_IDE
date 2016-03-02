package frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Portrait extends VisualComponent{
	private ImageView myPortrait;
	private double myAngle;
	private int myID;
	
	Portrait(int ID, String imgUrl){
		myPortrait = new ImageView(imgUrl);
		myAngle = 0;
		super.setVisual(myPortrait);
		myPortrait.setX(0);
		myPortrait.setY(0);
		myID = ID;
	}
	
	Portrait(int ID, Image img){
		myPortrait = new ImageView(img);
		myAngle = 0;
		super.setVisual(myPortrait);
		myPortrait.setX(0);
		myPortrait.setY(0);
		myID = ID;
	}
	
	void setCoor(double newX, double newY) {
		myPortrait.setX(newX);
		myPortrait.setY(newY);
	}
	
	void rotate(double angle){
		this.myPortrait.setRotate(angle);
	}
	
	ImageView getMyPortrait(){return this.myPortrait; }
	
	
}
