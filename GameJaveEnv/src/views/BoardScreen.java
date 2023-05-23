package views;
import java.util.Stack;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;

public class BoardScreen extends Application {
    private static final int TILE_SIZE = 40;
    private static final int MAP_SIZE = 15;

    public static void main(String[] args)
    {
        launch(args);
    }
    

    @Override
    public void start(Stage mainBoard)  throws Exception
    {
    
    BackgroundImage BI = new BackgroundImage(new Image("file:Resources/Images/background.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
    BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
    Background BG = new Background(BI);

    // StackPane selectedStats = new StackPane(); //shows the stats of the selected hero
    // StackPane briefStats = new StackPane(); //shows the breif stats of unselected hero
    // HBox fullScreenHBox = new HBox(2); //full Hsplit of the map, left = character desc / right = map
    // //child of fullscreen
    // VBox CharVBox = new VBox(2); // split of the left Vbox, top = selected chararcter desc / bot = brief cured character desc 
    // //child of selectedchar
    // HBox mainCharHBox = new HBox(); //split of the char desc Vbox to include image and stats
    // //child of fullscreen
    // HBox sideCharHBox = new HBox(); // split of the available heroes, includes brief desc + icon
    
    // TilePane mapTiles = new TilePane(); //hold the 15*15
    // StackPane Emptybase = new StackPane(); //empty tile goes into each cell regardless
    // StackPane ZombiePane = new StackPane(); 
    // StackPane collectible = new StackPane();    
    // StackPane heroPane = new StackPane();
    // StackPane visiblePane= new StackPane(); 
    // //StackPane borderPane = new StackPane(); QOL to be maybe implemented later?
   


    // CharVBox.getChildren().add(mainCharHBox);
    // CharVBox.getChildren().add(sideCharHBox);
    // fullScreenHBox.getChildren().add(CharVBox);

        HBox mapView = new HBox();
        mapView.setAlignment(Pos.CENTER_RIGHT);
        //TilePane mapArea = new TilePane(null, 0, 0, null);
        GridPane mapGrid = new GridPane();
        mapGrid.setPadding(new Insets(10));
        mapGrid.setHgap(5);
        mapGrid.setVgap(5);

        for(int row = 0; row < MAP_SIZE; row++)
        {
            for(int col = 0; col < MAP_SIZE; col++)
            {
                StackPane stackPane = createCell("emptybase");
                mapGrid.add(stackPane,col,row);
                mapView.getChildren().add(col, stackPane);
                //need to add check to see position of other stacks pane, create it then stack it
            }
        }



        mapView.setAlignment(Pos.CENTER_RIGHT);
        

    }

    private StackPane createCell(String type)
    {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(TILE_SIZE, TILE_SIZE);
        switch (type) 
        {
            case "emptyBase":
            Image img = new Image("file:Resources/Images/baseTile.png");
            ImageView imgView = new ImageView(img);
            stackPane.getChildren().add(imgView);
            break;

            case "zombiePane":
            Image img2 = new Image("file:Resources/Images/Zombie.png");
            ImageView imgView2 = new ImageView(img2);
            stackPane.getChildren().add(imgView2);
            break;

            case "collectible":
            Image img3 = new Image("file:Resources/Images/Zombie.png");
            ImageView imgView3 = new ImageView(img3);
            stackPane.getChildren().add(imgView3);
            break;

            case "heroPane":
            Image img4 = new Image("file:Resources/Images/heroes.png"); //place holder, need to handle the actual selected herp
            ImageView imgView4 = new ImageView(img4);
            stackPane.getChildren().add(imgView4);
            break;

            case "visiblePane":
            Image img5 = new Image("file:Resources/Images/Visibility.png");
            ImageView imgView5 = new ImageView(img5);
            stackPane.getChildren().add(imgView5);
            default:    
            break;

        }

       return stackPane;

    }



}
   
