package frontend;

import backend.data.Character;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Portrait extends VisualComponent{
	private ImageView myPortrait;
	private Character myChar;
	private int myID;
	public static int characterID = 0;
	
	public Portrait(Character c) {
		myPortrait = new ImageView(c.getMyImagePath());
		super.setVisual(myPortrait);
		myPortrait.setX(0);
		myPortrait.setY(0);
		myChar = c;
		myID = myChar.hashCode();
	}

	/**
	 * Sets the X and Y coordinates of a portrait.
	 * @param newX
	 * @param newY
	 */
	void setCoor(double newX, double newY) {
		myPortrait.setX(newX);
		myPortrait.setY(newY);
	}
	
	/**
	 * Sets the angle/heading of the portrait.
	 * @param angle
	 */
	void rotate(double angle){
		this.myPortrait.setRotate(angle);
	}
	
	public static int getAndIncrementId(){return characterID++;}
	
	double getAngle() {return this.myPortrait.getRotate();}
	ImageView getMyPortrait(){return this.myPortrait; }
	Character getMyChar(){return this.myChar;}
	
	
}