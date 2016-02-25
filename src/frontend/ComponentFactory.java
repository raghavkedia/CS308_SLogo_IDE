package frontend;

public class ComponentFactory {
	public static LabeledButton makeNewLabeledButton(double width, double height){
		return new LabeledButton(width, height);
	}
}
