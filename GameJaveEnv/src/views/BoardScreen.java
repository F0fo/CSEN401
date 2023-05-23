package views;
import java.util.Stack;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import engine.java;

public class BoardScreen extends Application implements EventHandler<mouseEvent> {


    public static void main(String[] args)
    {
        launch(args);
    }


    @Override
    public void start(Stage mainBoard)  throws Exception
    {
    StackPane selectedStats = new StackPane(); //shows the stats of the selected hero
    StackPane briefStats = new StackPane(); //shows the breif stats of unselected hero
    VBox fullScreenVBox = new VBox(2); //full Hsplit of the map, left = character desc / right = map
    //child of fullscreen
    HBox selectedCharVBox = new HBox(2); // split of the left Vbox, top = selected chararcter desc / bot = brief cured character desc 
    //child of selectedchar
    VBox selectedCharDescVBox = new VBox(2); //split of the char desc Vbox to include image and stats
    //child of fullscreen
    VBox availCharVBox = new VBox(availableHeroes.size()); // split of the available heroes, includes brief desc + icon
    }
}
   
