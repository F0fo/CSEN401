package views;

import java.awt.Point;

import engine.Game;
import javafx.event.Event;
import javafx.event.EventHandler;
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

    private static final int TILE_SIZE = 48;
    public static GridPane mapGrid = new GridPane();

    public static GridPane createInitialMap(){
        Image imgBase = new Image("file:Resources/Images/base.png");
        Image imgTop = new Image("file:Resources/Images/top.png");
        Image imgSupply = new Image("file:Resources/Images/supply.png");
        Image imgVaccine = new Image("file:Resources/Images/vaccine.png");
        Image imgZombie = new Image("file:Resources/Images/zombie.png");
        Image imgYellow = new Image("file:Resources/Images/border-yellow.png");
        Image imgTopYellow = new Image("file:Resources/Images/top-yellow.png");
        
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                StackPane s = new StackPane();
                
                ImageView imgBaseView = new ImageView(imgBase);
                imgBaseView.setFitHeight(TILE_SIZE);
                imgBaseView.setPreserveRatio(true);

                
                Button b = new Button();
                b.setMinSize(TILE_SIZE, TILE_SIZE);
                b.setMaxSize(TILE_SIZE, TILE_SIZE);
                b.setId("mapButton");
                b.addEventFilter(MouseEvent.MOUSE_PRESSED, UserInputs.mouseHandler);
                b.addEventFilter(KeyEvent.KEY_PRESSED, UserInputs.keyHandler);
                
                ImageView imgTopView = new ImageView(imgTop);
                imgTopView.setFitHeight(TILE_SIZE);
                imgTopView.setPreserveRatio(true);
                b.setGraphic(imgTopView);
                
                ImageView imgTopYellowView = new ImageView(imgTopYellow);
                imgTopYellowView.setFitHeight(TILE_SIZE);
                imgTopYellowView.setPreserveRatio(true);
                
                b.setOnMouseEntered(new EventHandler<Event>() {
                    public void handle(Event e) {
                        StackPane s = (StackPane)(((Button)e.getSource()).getParent());
                        Point p = UserInputs.findStackPane(s);
                        if(Game.map[p.x][p.y].isVisible()){
                            ImageView imgYellowView = new ImageView(imgYellow);
                            imgYellowView.setFitHeight(TILE_SIZE);
                            imgYellowView.setPreserveRatio(true);
                            b.setGraphic(imgYellowView);
                        }
                        else
                            b.setGraphic(imgTopYellowView);
                    }
                });
                b.setOnMouseExited(new EventHandler<Event>() {
                    public void handle(Event e) {
                        StackPane s = (StackPane)(((Button)e.getSource()).getParent());
                        Point p = UserInputs.findStackPane(s);
                        if(Game.map[p.x][p.y].isVisible()){
                            selectionBorder(mapGrid);
                            targetBorder(mapGrid);
                        }
                        else{
                            b.setGraphic(imgTopView);
                        }
                    }
                });

                s.getChildren().addAll(imgBaseView, b);

                if(Game.map[14 - i][j] instanceof CollectibleCell){
                    if(((CollectibleCell)Game.map[14 - i][j]).getCollectible() instanceof Supply){
                        ImageView imgSupplyView = new ImageView(imgSupply);
                        imgSupplyView.setFitHeight(TILE_SIZE);
                        imgSupplyView.setPreserveRatio(true);
                        s.getChildren().add(1, imgSupplyView);
                    }
                    else{
                        ImageView imgVaccineView = new ImageView(imgVaccine);
                        imgVaccineView.setFitHeight(TILE_SIZE);
                        imgVaccineView.setPreserveRatio(true);
                        s.getChildren().add(1, imgVaccineView);
                    }
                }
                else if(Game.map[14 - i][j] instanceof CharacterCell){
                    if(((CharacterCell)Game.map[14 - i][j]).getCharacter() instanceof Zombie){
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

    public static void selectionBorder(GridPane mapPane){
        Image imgGreen = new Image("file:Resources/Images/border-green.png");
        ImageView imgGreenView = new ImageView(imgGreen);
        imgGreenView.setFitHeight(TILE_SIZE);
        imgGreenView.setPreserveRatio(true);

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                StackPane s = getStackPane(j, i);
                Button b = (Button)s.getChildren().get(s.getChildren().size() - 1);
                if(Game.map[14 - i][j] instanceof CharacterCell && ((CharacterCell)Game.map[14 - i][j]).getCharacter() != null &&
                    ((CharacterCell)Game.map[14 - i][j]).getCharacter().equals(Main.selectedChar)){
                    b.getGraphic().setVisible(true);
                    b.setGraphic(imgGreenView);
                }
                else{
                    if(Game.map[14 - i][j].isVisible()){
                        if(Game.map[14 - i][j] instanceof CharacterCell && ((CharacterCell)Game.map[14 - i][j]).getCharacter() 
                            != Main.selectedChar.getTarget() || !(Game.map[14 - i][j] instanceof CharacterCell))
                            b.getGraphic().setVisible(false);
                    }
                }
            }
        }
    }

    public static void targetBorder(GridPane mapPane){
        Image imgRed = new Image("file:Resources/Images/border-red.png");
        ImageView imgRedView = new ImageView(imgRed);
        imgRedView.setFitHeight(TILE_SIZE);
        imgRedView.setPreserveRatio(true);

        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                StackPane s = getStackPane(j, i);
                Button b = (Button)s.getChildren().get(s.getChildren().size() - 1);
                if(Main.selectedChar.getTarget() != null && Game.map[14 - i][j] instanceof CharacterCell && 
                    ((CharacterCell)Game.map[14 - i][j]).getCharacter() != null &&
                    ((CharacterCell)Game.map[14 - i][j]).getCharacter().equals(Main.selectedChar.getTarget())){
                    if(Game.map[14 - i][j].isVisible()){
                        b.getGraphic().setVisible(true);
                        b.setGraphic(imgRedView);
                    }
                }
                else{
                    if(Game.map[14 - i][j].isVisible()){
                        if((Game.map[14 - i][j] instanceof CharacterCell && ((CharacterCell)Game.map[14 - i][j]).getCharacter() 
                            != Main.selectedChar) || !(Game.map[14 - i][j] instanceof CharacterCell)){
                            b.getGraphic().setVisible(false);
                        }
                    }
                }
            }
        }
    }

    public static void makeVisible(GridPane mapPane){
        Image imgTransparent = new Image("file:Resources/Images/transparent.png");
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(Game.map[14 - i][j].isVisible()){
                    StackPane s = getStackPane(j, i);
                    Button b = (Button)s.getChildren().get(s.getChildren().size() - 1);
                    
                    ImageView imgTransparentView = new ImageView(imgTransparent);
                    imgTransparentView.setFitHeight(TILE_SIZE);
                    imgTransparentView.setPreserveRatio(true);
                    b.setGraphic(imgTransparentView);
                }
            }
        }
    }

    public static void makeInvisible(GridPane mapPane){
        Image imgTop = new Image("file:Resources/Images/top.png");
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(!(Game.map[14 - i][j].isVisible())){
                    StackPane s = getStackPane(j, i);
                    Button b = (Button)s.getChildren().get(s.getChildren().size() - 1);
                    
                    ImageView imgTopView = new ImageView(imgTop);
                    imgTopView.setFitHeight(TILE_SIZE);
                    imgTopView.setPreserveRatio(true);
                    b.setGraphic(imgTopView);
                }
            }
        }
    }

    public static void zombieAdd(GridPane mapPane){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(Game.map[14 - i][j] instanceof CharacterCell && ((CharacterCell)Game.map[14 - i][j]).getCharacter() instanceof Zombie){
                    StackPane s = getStackPane(j, i);
                    if(!(s.getChildren().get(1) instanceof ImageView)){
                        Image imgZombie = new Image("file:Resources/Images/zombie.png");
                        ImageView imgZombieView = new ImageView(imgZombie);
                        imgZombieView.setFitHeight(TILE_SIZE);
                        imgZombieView.setPreserveRatio(true);
                        s.getChildren().add(1, imgZombieView);
                    }
                }
            }
        }
    }

    public static void characterRemove(GridPane mapPane){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(Game.map[14 - i][j] instanceof CharacterCell && ((CharacterCell)Game.map[14 - i][j]).getCharacter() == null){
                    StackPane s = getStackPane(j, i);
                    if((s.getChildren().get(1) instanceof ImageView)){
                        s.getChildren().remove(1);
                        for(int k = 0; k < Game.heroes.size(); k++){
                            if(Game.heroes.get(k).getTarget() != null){
                                if(Game.heroes.get(k).getTarget().getLocation().x == 14 - i && 
                                Game.heroes.get(k).getTarget().getLocation().y == j){
                                    Game.heroes.get(k).setTarget(null);
                                }
                            }
                        }
                        if(Main.selectedChar.getCurrentHp() <= 0){
                            if(Game.heroes.size() > 0){
                                Main.selectedChar = Game.heroes.get(0);
                            }
                            else
                            Main.selectedChar = null;
                            StatsManager.clearStats();
                            StatsManager.updateSelectedStats();
                            StatsManager.updateOtherStats();
                        }
                    }
                }
            }
        }
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
