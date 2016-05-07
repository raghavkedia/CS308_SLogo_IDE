package frontend.toobar;

import backend.data.*;
import controller.Controller;
import frontend.PopupWindow;
import frontend.listVisual.ListVisual;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by richardliu on 5/7/16.
 */
public class TurtleImageViewPopupWindow extends PopupWindow {
    private Controller myController;

    public TurtleImageViewPopupWindow(Controller control){
        super();
        myController = control;
        String title = "Turtle Image View Window";
        initBox(title);
    }

    public void initBox(String args){
        ListView<HBox> turtleList = new TurtleImageViewList(myController);
        ScrollPane scroller = new ScrollPane();
        scroller.setContent(turtleList);
        super.myBox.getChildren().add(scroller);
    }

    public VBox getMyBox(){ return this.myBox; }
}
