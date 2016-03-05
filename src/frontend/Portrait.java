package frontend;

import backend.data.Character;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

class Portrait extends VisualComponent{
	private ImageView myPortrait;
	private Character myChar;
	private int myID;
	
	public Portrait(Character c) {
		myPortrait = new ImageView(c.getMyImage());
		super.setVisual(myPortrait);
		myPortrait.setX(0);
		myPortrait.setY(0);
		myChar = c;
		myID = myChar.hashCode();
	}

	void setCoor(double newX, double newY) {
		myPortrait.setX(newX);
		myPortrait.setY(newY);
	}
	
	void rotate(double angle){
		this.myPortrait.setRotate(angle);
	}
	
	double getAngle() {return this.myPortrait.getRotate();}
	ImageView getMyPortrait(){return this.myPortrait; }
	Character getMyChar(){return this.myChar;}
	
	
}
