package frontend;

import javafx.scene.image.ImageView;

public class Portrait extends VisualComponent{
	private ImageView myPortrait;
	
	public Portrait(String imgUrl){
		myPortrait = new ImageView(imgUrl);
		super.setVisual(myPortrait);
	}
}
