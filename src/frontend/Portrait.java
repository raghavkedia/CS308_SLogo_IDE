package frontend;

import javafx.scene.image.ImageView;

public class Portrait extends VisualComponent{
	private ImageView myPortrait;
	private double myAngle;
	
	public Portrait(String imgUrl){
		myPortrait = new ImageView(imgUrl);
		myAngle = 0;
		super.setVisual(myPortrait);
	}
	
	public void rotate(double angle){
		this.myPortrait.setRotate(angle);
	}
	
	public ImageView getMyPortrait(){return this.myPortrait; }
}
