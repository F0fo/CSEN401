package views;

import engine.Game;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.world.CharacterCell;
import model.world.CollectibleCell;

public class Board {
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
                s.getChildren().add(imgBaseView);

                if(Game.map[j][14 - i] instanceof CollectibleCell){
                    if(((CollectibleCell)Game.map[j][14 - i]).getCollectible() instanceof Supply){
                        Image imgSupply = new Image("file:Resources/Images/supply.png");
                        ImageView imgSupplyView = new ImageView(imgSupply);
                        imgSupplyView.setFitHeight(TILE_SIZE);
                        imgSupplyView.setPreserveRatio(true);
                        s.getChildren().add(imgSupplyView);
                    }
                    else{
                        Image imgVaccine = new Image("file:Resources/Images/vaccine.png");
                        ImageView imgVaccineView = new ImageView(imgVaccine);
                        imgVaccineView.setFitHeight(TILE_SIZE);
                        imgVaccineView.setPreserveRatio(true);
                        s.getChildren().add(imgVaccineView);
                    }
                }
                else if(Game.map[j][14 - i] instanceof CharacterCell){
                    if(((CharacterCell)Game.map[j][14 - i]).getCharacter() instanceof Zombie){
                        Image imgZombie = new Image("file:Resources/Images/zombie.png");
                        ImageView imgZombieView = new ImageView(imgZombie);
                        imgZombieView.setFitHeight(TILE_SIZE);
                        imgZombieView.setPreserveRatio(true);
                        s.getChildren().add(imgZombieView);
                    }
                }
                mapGrid.add(s, j, 14 - i);
            }
        }

        mapGrid.setAlignment(Pos.CENTER);
        return mapGrid;
    }
}
