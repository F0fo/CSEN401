package views;
import java.util.Stack;

import engine.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.world.CharacterCell;

public class BoardScreen extends Application {
    private static final int TILE_SIZE = 48;
    private static final int MAP_SIZE = 15;
    public static GridPane mapGrid = new GridPane();

    public static void main(String[] args)
    {
        launch(args);
    }
    

    @Override
    public void start(Stage mainBoard)  throws Exception
    {

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

        
        //TilePane mapArea = new TilePane(null, 0, 0, null);
        //mapGrid = new GridPane();
        mapGrid.setPadding(new Insets(10));
        mapGrid.setHgap(1);
        mapGrid.setVgap(1);

        for(int row = 0; row < MAP_SIZE; row++)
        {
            for(int col = 0; col < MAP_SIZE; col++)
            {
                //StackPane stackPane = createCell("emptyBase");
                //mapGrid.add(stackPane,col,row);
                StackPane stackPane = createCell("emptyBase");
                mapGrid.add(stackPane,col, 14 - row);
                //need to add check to see position of other stacks pane, create it then stack it
            }
        }
        mapGrid.add(createCell("heroPane"), 0, 14 - 0);
        
        Scene scene = new Scene(mapGrid);
        mainBoard.setScene(scene);
        mainBoard.show();

    }

    public static GridPane createGrid(){
        for(int row = 0; row < MAP_SIZE; row++)
        {
            for(int col = 0; col < MAP_SIZE; col++)
            {
                StackPane stackPane = createCell("emptyBase");
                mapGrid.add(stackPane,col, 14 - row);
            }
        }
        addCharPane();

        return mapGrid;
    }

    public static void addCharPane(){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(Game.map[j][14 - i] instanceof CharacterCell && ((CharacterCell)Game.map[j][14 - i]).getCharacter() instanceof Hero){
                    Image img4 = new Image("file:Resources/Images/ellie.png");
                    ImageView imgView4 = new ImageView(img4);
                    getStackPane(j, i).getChildren().add(imgView4);
                    //mapGrid.add(, j, 14 - i);
                }
            }
        }
    }

    static StackPane getStackPane(int j, int i){
        for(Node node: mapGrid.getChildren()){
            if(GridPane.getRowIndex(node) == 14 - i && GridPane.getColumnIndex(node) == j){
                return (StackPane)node;
            }
        }
        return null;
    }

    private static StackPane createCell(String type)
    {
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(TILE_SIZE, TILE_SIZE);
        switch (type) 
        {
            case "emptyBase":
            Image img = new Image("file:Resources/Images/base.png");
            ImageView imgView = new ImageView(img);
            imgView.setFitHeight(TILE_SIZE);
            imgView.setPreserveRatio(true);
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
            Image img4 = new Image("file:Resources/Images/ellie.png"); //place holder, need to handle the actual selected herp
            ImageView imgView4 = new ImageView(img4);
            imgView4.setFitHeight(TILE_SIZE);
            imgView4.setPreserveRatio(true);
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

    // private ImageView createRectangle() {
    //     Image img = new Image("file:Resources/Images/base.png");
    //     ImageView imgView = new ImageView(img);
    //     imgView.setFitHeight(48);
    //     imgView.setPreserveRatio(true);
    //     return imgView;
    // }

}
   
