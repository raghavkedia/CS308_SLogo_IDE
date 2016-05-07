package frontend.toobar;

import controller.Controller;
import frontend.ComponentFactory;
import javafx.scene.control.Button;

/**
 * Created by richardliu on 5/7/16.
 */
public class TurtleImageViewButton extends Button {

    public TurtleImageViewButton(Controller myController){
        init(myController);
    }

    public void init(Controller controller){
        this.setText("Turtle Image View");
        this.setOnAction(
                e -> {
                    TurtleImageViewPopupWindow pw = new TurtleImageViewPopupWindow(controller);
                    ComponentFactory.initNewPopup(pw, 300, 600);
                });
    }

}
