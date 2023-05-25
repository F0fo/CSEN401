package views;

import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;
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
import javafx.stage.Stage;
import model.characters.Hero;
import model.characters.Zombie;

public class Main extends Application implements EventHandler<MouseEvent> {

    private Button[] charButtons = new Button[8];
    private ImageView[] charImgViews = new ImageView[8];
    public static ImageView[] charImgViewsBorder = new ImageView[8];
    public Label statsLabel = new Label();
    private ArrayList<Hero> AH;
    private HBox levelRoot;
    private Stage stage;
    private VBox statsRoot;

    public static VBox otherCharsRoot = new VBox();
    public static HBox otherCharsHbox1 = new HBox(3);
    public static HBox otherCharsHbox2 = new HBox(2);
    public static VBox otherCharRoot = new VBox();

    public static Label otherCharImg1 = new Label();
    public static Label otherCharImg2 = new Label();
    public static Label otherCharImg3 = new Label();
    public static Label otherCharImg4 = new Label();
    public static Label otherCharImg5 = new Label();
    public static Label[] otherCharImgs = {otherCharImg1, otherCharImg2, otherCharImg3, otherCharImg4, otherCharImg5};

    public static Label otherCharText1 = new Label();
    public static Label otherCharText2 = new Label();
    public static Label otherCharText3 = new Label();
    public static Label otherCharText4 = new Label();
    public static Label otherCharText5 = new Label();
    public static Label[] otherCharTexts = {otherCharText1, otherCharText2, otherCharText3, otherCharText4, otherCharText5};
    
    public static HBox selectedCharRoot;
    public static Hero selectedChar;
    public static Label selectedCharStats1 = new Label();
    public static Label selectedCharStats2 = new Label();
    public static Label selectedCharImg = new Label();
    

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        // creating start menu scene and stack pane
        this.stage = stage;
        StackPane menuRoot = new StackPane();
        StackPane charSelectRoot = new StackPane();
        VBox left = new VBox();
        VBox right = new VBox();
        Scene scene = new Scene(menuRoot);

        // creating background image
        BackgroundImage BI = new BackgroundImage(new Image("file:Resources/Images/background.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background BG = new Background(BI);

        // creating title and subtitle
        Font pressGothic = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 120);
        Label title = new Label("THE\nLAST\nOF US:");
        Label subtitle = new Label("LEGACY");
        subtitle.setId("subtitle");



        // creating start and quit buttons
        Button start = new Button("Start Game");
        Button quit = new Button("Quit");
        start.setBackground(null);
        quit.setBackground(null);
        
       

        start.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                stage.getScene().setRoot(charSelectRoot);
            }
        });
        start.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                start.setText("> Start Game");
            }
        });
        start.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                start.setText("Start Game");
            }
        });

        quit.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });
        quit.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                quit.setText("> Quit");
            }
        });
        quit.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                quit.setText("Quit");
            }
        });

        // changing icon, setting window title, and setting window to fullscreen
        Image icon = new Image("file:Resources/Images/icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("The Last of Us: Legacy");
        stage.setFullScreen(true);
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        // adding title and buttons
        left.getChildren().addAll(title, subtitle);
        right.getChildren().addAll(start, quit);
        right.setSpacing(10);

        // adding components to vbox
        left.setAlignment(Pos.BOTTOM_LEFT);
        left.setPadding(new Insets(80));
        right.setAlignment(Pos.BOTTOM_RIGHT);
        right.setPadding(new Insets(80));

        // adding components to stack pane
        menuRoot.setBackground(BG);
        menuRoot.getChildren().addAll(left, right);

        // ------------------------------------------------------------------------------------------------------

        // creating character selection node
        AnchorPane chars = new AnchorPane();
        charSelectRoot.setBackground(BG);

        // creating array of character images
        Image[] charImg = new Image[16];
        charImg[0] = new Image("file:Resources/Images/joel-big.png");
        charImg[1] = new Image("file:Resources/Images/ellie-big.png");
        charImg[2] = new Image("file:Resources/Images/tess-big.png");
        charImg[3] = new Image("file:Resources/Images/riley-big.png");
        charImg[4] = new Image("file:Resources/Images/tommy-big.png");
        charImg[5] = new Image("file:Resources/Images/bill-big.png");
        charImg[6] = new Image("file:Resources/Images/david-big.png");
        charImg[7] = new Image("file:Resources/Images/henry-big.png");
        charImg[8] = new Image("file:Resources/Images/joel-big-border.png");
        charImg[9] = new Image("file:Resources/Images/ellie-big-border.png");
        charImg[10] = new Image("file:Resources/Images/tess-big-border.png");
        charImg[11] = new Image("file:Resources/Images/riley-big-border.png");
        charImg[12] = new Image("file:Resources/Images/tommy-big-border.png");
        charImg[13] = new Image("file:Resources/Images/bill-big-border.png");
        charImg[14] = new Image("file:Resources/Images/david-big-border.png");
        charImg[15] = new Image("file:Resources/Images/henry-big-border.png");
        for(int i = 0; i < 8; i++){
            charImgViews[i] = new ImageView(charImg[i]);
            charImgViews[i].setFitHeight(270);
            charImgViews[i].setPreserveRatio(true);
        }
        for(int i = 8; i < 16; i++){
            charImgViewsBorder[i - 8] = new ImageView(charImg[i]);
            charImgViewsBorder[i - 8].setFitHeight(300);
            charImgViewsBorder[i - 8].setPreserveRatio(true);
        }

        // creating button for each character and adding to node
        for(int i = 0; i < 8; i++){
            charButtons[i] = new Button();
            charButtons[i].setMinSize(180, 270);
            charButtons[i].setMaxSize(180, 270);
            charButtons[i].setOnMouseEntered(this);
            charButtons[i].setOnMouseExited(this);
            charButtons[i].setOnMousePressed(this);
            charButtons[i].setGraphic(charImgViews[i]);
            charButtons[i].setContentDisplay(ContentDisplay.BOTTOM);
            charButtons[i].setId("characterButton");
            chars.getChildren().add(charButtons[i]);
        }
        charSelectRoot.getChildren().addAll(chars);

        // setting button locations
        charButtons[0].setTranslateX(40); charButtons[0].setTranslateY(240);
        charButtons[1].setTranslateX(340); charButtons[1].setTranslateY(240);
        charButtons[2].setTranslateX(640); charButtons[2].setTranslateY(240);
        charButtons[3].setTranslateX(940); charButtons[3].setTranslateY(240);
        charButtons[4].setTranslateX(40); charButtons[4].setTranslateY(540);
        charButtons[5].setTranslateX(340); charButtons[5].setTranslateY(540);
        charButtons[6].setTranslateX(640); charButtons[6].setTranslateY(540);
        charButtons[7].setTranslateX(940); charButtons[7].setTranslateY(540);

        // scene title
        Label selectCharLabel = new Label("Select a hero!");
        selectCharLabel.setId("subtitle");
        charSelectRoot.getChildren().add(selectCharLabel);
        selectCharLabel.setTranslateX(-530); selectCharLabel.setTranslateY(-300);

        // loading heroes from csv
        Game.loadHeroes("Resources/Other/Heros.csv");
        AH = Game.availableHeroes;
        charSelectRoot.getChildren().add(statsLabel);
        statsLabel.setId("stats");
        statsLabel.setTranslateX(530); statsLabel.setTranslateY(0);
        
        // ------------------------------------------------------------------------------------------------------

        // creating level root char stats root
        levelRoot = new HBox(2);
        levelRoot.setBackground(BG);
        statsRoot = new VBox(2);
        statsRoot.setPadding(new Insets(60));
        levelRoot.getChildren().add(statsRoot);

        // creating selected char stats area
        selectedCharRoot = new HBox(3);
        selectedCharRoot.setSpacing(20);

        // ------------------------------------------------------------------------------------------------------

        // adding scene to stage
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
        stage.show();
    }

    public void charStatsManager(int n){

        StatsManager.updateSelectedStats();
        StatsManager.updateOtherStats();

        selectedCharRoot.getChildren().addAll(selectedCharImg, selectedCharStats1, selectedCharStats2);
        selectedCharStats1.setId("selectedChar");
        selectedCharStats2.setId("selectedChar");

        VBox otherChar1 = new VBox();
        otherChar1.getChildren().addAll(otherCharImg1, otherCharText1);
        VBox otherChar2 = new VBox();
        otherChar2.getChildren().addAll(otherCharImg2, otherCharText2);
        VBox otherChar3 = new VBox();
        otherChar3.getChildren().addAll(otherCharImg3, otherCharText3);
        VBox otherChar4 = new VBox();
        otherChar4.getChildren().addAll(otherCharImg4, otherCharText4);
        VBox otherChar5 = new VBox();
        otherChar5.getChildren().addAll(otherCharImg5, otherCharText5);

        otherChar1.setAlignment(Pos.CENTER);
        otherChar2.setAlignment(Pos.CENTER);
        otherChar3.setAlignment(Pos.CENTER);
        otherChar4.setAlignment(Pos.CENTER);
        otherChar5.setAlignment(Pos.CENTER);

        otherCharText1.setId("otherChar");
        otherCharText2.setId("otherChar");
        otherCharText3.setId("otherChar");
        otherCharText4.setId("otherChar");
        otherCharText5.setId("otherChar");

        otherCharsHbox1.getChildren().addAll(otherChar1, otherChar2, otherChar3);
        otherCharsHbox2.getChildren().addAll(otherChar4, otherChar5);

        otherCharsHbox1.setSpacing(100);
        otherCharsHbox2.setSpacing(100);
        
        otherCharsRoot.getChildren().addAll(otherCharsHbox1, otherCharsHbox2);
        otherCharsRoot.setSpacing(20);

        statsRoot.getChildren().addAll(selectedCharRoot, otherCharsRoot);
    }

    public void gameStart(int n){
        Game.startGame(Game.availableHeroes.get(n));
        stage.getScene().setRoot(levelRoot);
        selectedChar = Game.heroes.get(0);
        charStatsManager(n);
        GridPane mapGrid = Board.createInitialMap();
        mapGrid = Board.heroManager(mapGrid);
        levelRoot.getChildren().add(mapGrid);
        Game.printMap(Game.map);
    }

    public void handle(MouseEvent mouseEvent){
        if(mouseEvent.getSource() == charButtons[0]){
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                charButtons[0].setGraphic(charImgViewsBorder[0]);
                statsLabel.setText("Name: " + AH.get(0).getName() + "\nType: " + AH.get(0).getClass().getSimpleName() + 
                "\nMax HP: " + AH.get(0).getMaxHp() + "\nMax Actions: " + AH.get(0).getMaxActions() + "\nAttack Damage: " 
                + AH.get(0).getAttackDmg());
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                charButtons[0].setGraphic(charImgViews[0]);
                statsLabel.setText("");
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                gameStart(0);
            }
        }
        if(mouseEvent.getSource() == charButtons[1]){
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                charButtons[1].setGraphic(charImgViewsBorder[1]);
                statsLabel.setText("Name: " + AH.get(1).getName() + "\nType: " + AH.get(1).getClass().getSimpleName() + 
                "\nMax HP: " + AH.get(1).getMaxHp() + "\nMax Actions: " + AH.get(1).getMaxActions() + "\nAttack Damage: " 
                + AH.get(1).getAttackDmg());
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                charButtons[1].setGraphic(charImgViews[1]);
                statsLabel.setText("");
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                gameStart(1);
            }
        }
        if(mouseEvent.getSource() == charButtons[2]){
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                charButtons[2].setGraphic(charImgViewsBorder[2]);
                statsLabel.setText("Name: " + AH.get(2).getName() + "\nType: " + AH.get(2).getClass().getSimpleName() + 
                "\nMax HP: " + AH.get(2).getMaxHp() + "\nMax Actions: " + AH.get(2).getMaxActions() + "\nAttack Damage: " 
                + AH.get(2).getAttackDmg());
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                charButtons[2].setGraphic(charImgViews[2]);
                statsLabel.setText("");
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                gameStart(2);
            }
        }
        if(mouseEvent.getSource() == charButtons[3]){
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                charButtons[3].setGraphic(charImgViewsBorder[3]);
                statsLabel.setText("Name: " + AH.get(3).getName() + "\nType: " + AH.get(3).getClass().getSimpleName() + 
                "\nMax HP: " + AH.get(3).getMaxHp() + "\nMax Actions: " + AH.get(3).getMaxActions() + "\nAttack Damage: " 
                + AH.get(3).getAttackDmg());
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                charButtons[3].setGraphic(charImgViews[3]);
                statsLabel.setText("");
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                gameStart(3);
            }
        }
        if(mouseEvent.getSource() == charButtons[4]){
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                charButtons[4].setGraphic(charImgViewsBorder[4]);
                statsLabel.setText("Name: " + AH.get(4).getName() + "\nType: " + AH.get(4).getClass().getSimpleName() + 
                "\nMax HP: " + AH.get(4).getMaxHp() + "\nMax Actions: " + AH.get(4).getMaxActions() + "\nAttack Damage: " 
                + AH.get(4).getAttackDmg());
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                charButtons[4].setGraphic(charImgViews[4]);
                statsLabel.setText("");
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                gameStart(4);
            }
        }
        if(mouseEvent.getSource() == charButtons[5]){
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                charButtons[5].setGraphic(charImgViewsBorder[5]);
                statsLabel.setText("Name: " + AH.get(5).getName() + "\nType: " + AH.get(5).getClass().getSimpleName() + 
                "\nMax HP: " + AH.get(5).getMaxHp() + "\nMax Actions: " + AH.get(5).getMaxActions() + "\nAttack Damage: " 
                + AH.get(5).getAttackDmg());
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                charButtons[5].setGraphic(charImgViews[5]);
                statsLabel.setText("");
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                gameStart(5);
            }
        }
        if(mouseEvent.getSource() == charButtons[6]){
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                charButtons[6].setGraphic(charImgViewsBorder[6]);
                statsLabel.setText("Name: " + AH.get(6).getName() + "\nType: " + AH.get(6).getClass().getSimpleName() + 
                "\nMax HP: " + AH.get(6).getMaxHp() + "\nMax Actions: " + AH.get(6).getMaxActions() + "\nAttack Damage: " 
                + AH.get(6).getAttackDmg());
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                charButtons[6].setGraphic(charImgViews[6]);
                statsLabel.setText("");
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                gameStart(6);
            }
        }
        if(mouseEvent.getSource() == charButtons[7]){
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                charButtons[7].setGraphic(charImgViewsBorder[7]);
                statsLabel.setText("Name: " + AH.get(7).getName() + "\nType: " + AH.get(7).getClass().getSimpleName() + 
                "\nMax HP: " + AH.get(7).getMaxHp() + "\nMax Actions: " + AH.get(7).getMaxActions() + "\nAttack Damage: " 
                + AH.get(7).getAttackDmg());
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_EXITED) {
                charButtons[7].setGraphic(charImgViews[7]);
                statsLabel.setText("");
            }
            else if(mouseEvent.getEventType() == MouseEvent.MOUSE_PRESSED) {
                gameStart(7);
            }
        }
    }
}