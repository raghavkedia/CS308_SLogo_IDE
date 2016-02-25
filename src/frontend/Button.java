package frontend;

abstract class Button extends VisualComponent implements IClickable{
	Button(double width, double height){
		super(width, height);
	}

	@Override
	public void respondToClick(){};
}
