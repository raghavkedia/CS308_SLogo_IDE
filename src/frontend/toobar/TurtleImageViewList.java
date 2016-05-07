package frontend.toobar;

import controller.Controller;
import backend.data.Character;
import frontend.GUI.Init;
import frontend.listVisual.ListVisual;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by richardliu on 5/7/16.
 */
public class TurtleImageViewList extends ListView<HBox> {
    private List<Character> myCharacters;
    private Controller myController;

    public TurtleImageViewList(Controller control){
        myController = control;
        myCharacters = new ArrayList<Character>();

        Collection<Character> allChars = control.getFrontendManager().getCharMap().values();
        myCharacters.addAll(allChars);
        populateList();


        super.setOnMouseClicked(e -> respondToClick());
    }

    public void populateList(){
        for (Character c : myCharacters){
            HBox entry = new HBox();
            entry.getChildren().add(new Text(c.getName()));

            ImageView turtleImg = new ImageView(c.getMyImagePath());
            turtleImg.setFitWidth(50);
            turtleImg.setFitHeight(50);
            entry.getChildren().add(turtleImg);

            super.getItems().add(entry);
        }
    }

    public void respondToClick(){
        int index = super.getSelectionModel().getSelectedIndex();

        FileChooser fc = new FileChooser();
        fc.setTitle(myController.getGUIProperty(Init.GUIString.PORTRAIT_TITLE.getKey()));
        fc.getExtensionFilters().setAll(new FileChooser.ExtensionFilter("Image Files (.png)", "*.png"));
        File imgFile = fc.showOpenDialog(myController.getMyStage());
        if (imgFile != null) {

        }
    }

    public void update(){
        super.getChildren().clear();
        populateList();
    }
}
