package frontend;

abstract class Button extends VisualComponent implements IClickable{
	Button(double w, double h){
		super(w, h);
	}

	@Override
	public void respondToClick(){};
}
