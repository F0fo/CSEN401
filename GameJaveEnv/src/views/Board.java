package views;

import engine.Game;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import model.characters.Hero;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.world.CharacterCell;
import model.world.CollectibleCell;

public class Board {
    // if visible:
    // Layer 0: base
    // Layer 1: button if empty, otherwise vaccine/supply/zombie/character
    // Layer 2: button if layer 1 was vaccine/supply/zombie/character

    // if not visible:
    // Layer 0: base
    // Layer 1: visibility if empty, otherwise vaccine/supply/zombie/character
    // Layer 2: visibility if layer 1 was vaccine/supply/zombie/character, button otherwise
    // Layer 3: button if layer 2 was visibility

    private static final int TILE_SIZE = 48;
    public static GridPane mapGrid = new GridPane();

    public static GridPane createInitialMap(){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                StackPane s = new StackPane();
                Image imgBase = new Image("file:Resources/Images/base.png");
                ImageView imgBaseView = new ImageView(imgBase);
                imgBaseView.setFitHeight(TILE_SIZE);
                imgBaseView.setPreserveRatio(true);

                Button b = new Button();
                b.setMinSize(TILE_SIZE, TILE_SIZE);
                b.setMaxSize(TILE_SIZE, TILE_SIZE);
                b.setId("mapButton");
                b.addEventFilter(MouseEvent.MOUSE_PRESSED, UserInputs.mouseHandler);
                b.addEventFilter(KeyEvent.KEY_PRESSED, UserInputs.keyHandler);

                s.getChildren().addAll(imgBaseView, b);

                if(Game.map[14 - i][j] instanceof CollectibleCell){
                    if(((CollectibleCell)Game.map[14 - i][j]).getCollectible() instanceof Supply){
                        Image imgSupply = new Image("file:Resources/Images/supply.png");
                        ImageView imgSupplyView = new ImageView(imgSupply);
                        imgSupplyView.setFitHeight(TILE_SIZE);
                        imgSupplyView.setPreserveRatio(true);
                        s.getChildren().add(1, imgSupplyView);
                    }
                    else{
                        Image imgVaccine = new Image("file:Resources/Images/vaccine.png");
                        ImageView imgVaccineView = new ImageView(imgVaccine);
                        imgVaccineView.setFitHeight(TILE_SIZE);
                        imgVaccineView.setPreserveRatio(true);
                        s.getChildren().add(1, imgVaccineView);
                    }
                }
                else if(Game.map[14 - i][j] instanceof CharacterCell){
                    if(((CharacterCell)Game.map[14 - i][j]).getCharacter() instanceof Zombie){
                        Image imgZombie = new Image("file:Resources/Images/zombie.png");
                        ImageView imgZombieView = new ImageView(imgZombie);
                        imgZombieView.setFitHeight(TILE_SIZE);
                        imgZombieView.setPreserveRatio(true);
                        s.getChildren().add(1, imgZombieView);
                    }
                }
                mapGrid.add(s, j, i);
            }
        }

        mapGrid.setAlignment(Pos.CENTER_RIGHT);
        return mapGrid;
    }

    public static GridPane heroManager(GridPane mapPane){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(Game.map[14 - i][j] instanceof CharacterCell && ((CharacterCell)Game.map[14 - i][j]).getCharacter() instanceof Hero){
                    StackPane s = getStackPane(j, i);
                    String name = ((CharacterCell)Game.map[14 - i][j]).getCharacter().getName();
                    Image img;
                    ImageView imgView;
                    String path = "";

                    if(name.equals("Joel Miller")){
                        path = "file:Resources/Images/joel.png";
                    }
                    else if(name.equals("Ellie Williams")){
                        path = "file:Resources/Images/ellie.png";
                    }
                    else if(name.equals("Tess")){
                        path = "file:Resources/Images/tess.png";
                    }
                    else if(name.equals("Riley Abel")){
                        path = "file:Resources/Images/riley.png";
                    }
                    else if(name.equals("Tommy Miller")){
                        path = "file:Resources/Images/tommy.png";
                    }
                    else if(name.equals("Bill")){
                        path = "file:Resources/Images/bill.png";
                    }
                    else if(name.equals("David")){
                        path = "file:Resources/Images/david.png";
                    }
                    else if(name.equals("Henry Burell")){
                        path = "file:Resources/Images/henry.png";
                    }

                    img = new Image(path);
                    imgView = new ImageView(img);
                    imgView.setFitHeight(TILE_SIZE);
                    imgView.setPreserveRatio(true);
                    
                    if(s.getChildren().get(1) instanceof ImageView)
                        s.getChildren().remove(1);
                    s.getChildren().add(1, imgView);
                }
            }
        }

        return mapPane;
    }

    public static StackPane getStackPane(int j, int i){
        for(Node node: mapGrid.getChildren()){
            if(GridPane.getRowIndex(node) == i && GridPane.getColumnIndex(node) == j){
                return (StackPane)node;
            }
        }
        return null;
    }
}
