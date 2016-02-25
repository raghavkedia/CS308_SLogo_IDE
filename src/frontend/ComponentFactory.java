package frontend;

public class ComponentFactory {
	public static Button makeNewButton(double height, double width){
		return new Button(height, width);
	}
}
