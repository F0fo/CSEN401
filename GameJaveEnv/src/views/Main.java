package views;

import java.util.ArrayList;

import engine.Game;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
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
import javafx.stage.Screen;
import javafx.stage.Stage;
import model.characters.Hero;

public class Main extends Application implements EventHandler<MouseEvent> {

    private Button[] charButtons = new Button[8];
    private ImageView[] charImgViews = new ImageView[8];
    public static ImageView[] charImgViewsBorder = new ImageView[8];
    public Label statsLabel = new Label();
    private ArrayList<Hero> AH;
    private StackPane levelRoot;
    private static Stage stage;
    private VBox statsRoot;
    private static StackPane menuRoot;

    public static VBox otherCharsRoot = new VBox();
    public static HBox otherCharsHbox1 = new HBox(3);
    public static HBox otherCharsHbox2 = new HBox();
    public static HBox otherCharsHbox3 = new HBox();
    public static VBox otherCharRoot = new VBox();

    public static Label otherCharImg1 = new Label();
    public static Label otherCharImg2 = new Label();
    public static Label otherCharImg3 = new Label();
    public static Label otherCharImg4 = new Label();
    public static Label otherCharImg5 = new Label();
    public static Label targetImg = new Label();
    public static Label[] otherCharImgs = {otherCharImg1, otherCharImg2, otherCharImg3, otherCharImg4, otherCharImg5};

    public static Label otherCharText1 = new Label();
    public static Label otherCharText2 = new Label();
    public static Label otherCharText3 = new Label();
    public static Label otherCharText4 = new Label();
    public static Label otherCharText5 = new Label();
    public static Label targetText = new Label();
    public static Label[] otherCharTexts = {otherCharText1, otherCharText2, otherCharText3, otherCharText4, otherCharText5};
    
    public static HBox selectedCharRoot;
    public static Hero selectedChar;
    public static Label selectedCharStats1 = new Label();
    public static Label selectedCharStats2 = new Label();
    public static Label selectedCharImg = new Label();

    public static Label exceptionLabel = new Label();
    public static Label exceptionBox = new Label();

    public static final double factor = Screen.getPrimary().getVisualBounds().getWidth() / 1536;
    

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        // creating start menu scene and stack pane
        this.stage = stage;
        menuRoot = new StackPane();
        StackPane charSelectRoot = new StackPane();
        VBox left = new VBox();
        VBox right = new VBox();
        Scene scene = new Scene(menuRoot);

        // creating background image
        BackgroundImage BI = new BackgroundImage(new Image("file:Resources/Images/background.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        Background BG = new Background(BI);

        // creating title and subtitle
        Font titleFont = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 120 * factor);
        Label title = new Label("THE\nLAST\nOF US:");
        title.setFont(titleFont);

        Font subtitleFont = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 80 * factor);
        Label subtitle = new Label("LEGACY");
        subtitle.setFont(subtitleFont);
        //subtitle.setId("subtitle");



        // creating start and quit buttons
        Button start = new Button("Start Game");
        Button quit = new Button("Quit");
        Button controls = new Button("Controls");
        start.setBackground(null);
        quit.setBackground(null);
        controls.setBackground(null);
        start.setPadding(new Insets(0));
        quit.setPadding(new Insets(0));
        controls.setPadding(new Insets(0));
        Font buttonFont = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 60 * factor);
        start.setFont(buttonFont);
        quit.setFont(buttonFont);
        controls.setFont(buttonFont);
        
       

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

        StackPane controlsRoot = new StackPane();
        controlsRoot.setId("controlsRoot");

        controls.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                stage.getScene().setRoot(controlsRoot);
            }
        });
        controls.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                controls.setText("> Controls");
            }
        });
        controls.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                controls.setText("Controls");
            }
        });

        Button backToMenu = new Button("Back to Menu");
        backToMenu.setBackground(null);
        backToMenu.setPadding(new Insets(0));
        controlsRoot.setAlignment(Pos.BOTTOM_RIGHT);
        controlsRoot.setPadding(new Insets(0, 70 * factor, 50 * factor, 0));
        controlsRoot.getChildren().add(backToMenu);

        backToMenu.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                stage.getScene().setRoot(menuRoot);
            }
        });
        backToMenu.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                backToMenu.setText("> Back to Menu");
            }
        });
        backToMenu.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                backToMenu.setText("Back to Menu");
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
        right.getChildren().addAll(start, controls, quit);
        right.setSpacing(30);

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
            charImgViews[i].setFitHeight(270 * factor);
            charImgViews[i].setPreserveRatio(true);
        }
        for(int i = 8; i < 16; i++){
            charImgViewsBorder[i - 8] = new ImageView(charImg[i]);
            charImgViewsBorder[i - 8].setFitHeight(300 * factor);
            charImgViewsBorder[i - 8].setPreserveRatio(true);
        }

        // creating button for each character and adding to node
        for(int i = 0; i < 8; i++){
            charButtons[i] = new Button();
            charButtons[i].setMinSize(180 * factor, 270 * factor);
            charButtons[i].setMaxSize(180 * factor, 270 * factor);
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
        charButtons[0].setTranslateX(40 * factor); charButtons[0].setTranslateY(240 * factor);
        charButtons[1].setTranslateX(340 * factor); charButtons[1].setTranslateY(240 * factor);
        charButtons[2].setTranslateX(640 * factor); charButtons[2].setTranslateY(240 * factor);
        charButtons[3].setTranslateX(940 * factor); charButtons[3].setTranslateY(240 * factor);
        charButtons[4].setTranslateX(40 * factor); charButtons[4].setTranslateY(540 * factor);
        charButtons[5].setTranslateX(340 * factor); charButtons[5].setTranslateY(540 * factor);
        charButtons[6].setTranslateX(640 * factor); charButtons[6].setTranslateY(540 * factor);
        charButtons[7].setTranslateX(940 * factor); charButtons[7].setTranslateY(540 * factor);

        // scene title
        Label selectCharLabel = new Label("Select a hero!");
        selectCharLabel.setFont(subtitleFont);
        charSelectRoot.getChildren().add(selectCharLabel);
        selectCharLabel.setTranslateX(-200 * factor); selectCharLabel.setTranslateY(-300 * factor);

        // loading heroes from csv
        Game.loadHeroes("Resources/Other/Heros.csv");
        AH = Game.availableHeroes;
        charSelectRoot.getChildren().add(statsLabel);
        Font statsFont = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 50 * factor);
        statsLabel.setFont(statsFont);
        statsLabel.setId("stats");
        statsLabel.setTranslateX(530 * factor); statsLabel.setTranslateY(80 * factor);
        
        // ------------------------------------------------------------------------------------------------------

        // creating level root char stats root
        levelRoot = new StackPane();
        levelRoot.setBackground(BG);
        statsRoot = new VBox(2);
        statsRoot.setPadding(new Insets(60));
        levelRoot.getChildren().add(statsRoot);
        statsRoot.setTranslateX(0); statsRoot.setTranslateY(0);

        // creating selected char stats area
        selectedCharRoot = new HBox(3);
        selectedCharRoot.setSpacing(20 * factor);

        // ------------------------------------------------------------------------------------------------------

        // adding scene to stage
        Image cursorImg = new Image("file:Resources/Images/cursor.png");
        scene.setCursor(new ImageCursor(cursorImg));
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("Styling.css").toExternalForm());
        stage.show();
    }

    public void charStatsManager(int n){

        StatsManager.updateSelectedStats();
        StatsManager.updateOtherStats();

        Font selectedCharFont = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 40 * factor);

        selectedCharRoot.getChildren().addAll(selectedCharImg, selectedCharStats1, selectedCharStats2);
        selectedCharStats1.setFont(selectedCharFont);
        selectedCharStats2.setFont(selectedCharFont);

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
        VBox targetCharBox = new VBox();
        targetCharBox.getChildren().addAll(targetImg, targetText);

        otherChar1.setAlignment(Pos.CENTER);
        otherChar2.setAlignment(Pos.CENTER);
        otherChar3.setAlignment(Pos.CENTER);
        otherChar4.setAlignment(Pos.CENTER);
        otherChar5.setAlignment(Pos.CENTER);
        targetCharBox.setAlignment(Pos.CENTER);

        Font otherCharFont = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 22 * factor);
        otherCharText1.setFont(otherCharFont);
        otherCharText2.setFont(otherCharFont);
        otherCharText3.setFont(otherCharFont);
        otherCharText4.setFont(otherCharFont);
        otherCharText5.setFont(otherCharFont);
        targetText.setFont(otherCharFont);

        otherCharText1.setId("otherChar");
        otherCharText2.setId("otherChar");
        otherCharText3.setId("otherChar");
        otherCharText4.setId("otherChar");
        otherCharText5.setId("otherChar");
        targetText.setId("otherChar");

        otherCharsHbox1.getChildren().addAll(otherChar1, otherChar2, otherChar3);
        otherCharsHbox2.getChildren().addAll(otherChar4, otherChar5, targetCharBox);
        otherCharsHbox3.getChildren().add(targetCharBox);

        otherCharsHbox1.setSpacing(100 * factor);
        otherCharsHbox2.setSpacing(100 * factor);
        otherCharsHbox3.setSpacing(100 * factor);
        
        otherCharsRoot.getChildren().addAll(otherCharsHbox1, otherCharsHbox2, otherCharsHbox3);
        otherCharsRoot.setSpacing(20 * factor);

        statsRoot.getChildren().addAll(selectedCharRoot, otherCharsRoot);
        statsRoot.setSpacing(30 * factor);
    }

    public void gameStart(int n){
        Game.startGame(Game.availableHeroes.get(n));
        stage.getScene().setRoot(levelRoot);
        selectedChar = Game.heroes.get(0);
        charStatsManager(n);

        GridPane mapGrid = Board.createInitialMap();
        mapGrid = Board.heroManager(mapGrid);
        Board.makeVisible(mapGrid);
        
        Board.selectionBorder(mapGrid);

        levelRoot.getChildren().add(mapGrid);
        mapGrid.setTranslateX(-130 * factor); mapGrid.setTranslateY(-30 * factor);

        exceptionLabel.setId("selectedChar");
        Font exceptionFont = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 30 * factor);
        exceptionLabel.setFont(exceptionFont);
        
        Image exceptionImg = new Image("file:Resources/Images/text.png");
        ImageView exceptionImgView = new ImageView(exceptionImg);
        exceptionImgView.setFitWidth(450 * factor);
        exceptionImgView.setPreserveRatio(true);

        exceptionBox.setGraphic(exceptionImgView);
        exceptionBox.getGraphic().setVisible(false);
        exceptionBox.setMouseTransparent(true);
        
        levelRoot.getChildren().addAll(exceptionBox, exceptionLabel);
        exceptionLabel.setTranslateX(280 * factor); exceptionLabel.setTranslateY(380 * factor);
        exceptionBox.setTranslateX(280 * factor); exceptionImgView.setTranslateY(383 * factor);

        Game.printMap(Game.map);
    }

    public void handle(MouseEvent mouseEvent){
        if(mouseEvent.getSource() == charButtons[0]){
            if(mouseEvent.getEventType() == MouseEvent.MOUSE_ENTERED) {
                charButtons[0].setGraphic(charImgViewsBorder[0]);
                statsLabel.setText(AH.get(0).getName() + "\nType: " + AH.get(0).getClass().getSimpleName() + 
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
                statsLabel.setText(AH.get(1).getName() + "\nType: " + AH.get(1).getClass().getSimpleName() + 
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
                statsLabel.setText(AH.get(2).getName() + "\nType: " + AH.get(2).getClass().getSimpleName() + 
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
                statsLabel.setText(AH.get(3).getName() + "\nType: " + AH.get(3).getClass().getSimpleName() + 
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
                statsLabel.setText(AH.get(4).getName() + "\nType: " + AH.get(4).getClass().getSimpleName() + 
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
                statsLabel.setText(AH.get(5).getName() + "\nType: " + AH.get(5).getClass().getSimpleName() + 
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
                statsLabel.setText(AH.get(6).getName() + "\nType: " + AH.get(6).getClass().getSimpleName() + 
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
                statsLabel.setText(AH.get(7).getName() + "\nType: " + AH.get(7).getClass().getSimpleName() + 
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

    public static void checkWin(){
        if (Game.checkWin()){
            Image youWin = new Image("file:Resources/Images/win.png");
            Button quit = new Button("Quit Game");
            quit.setBackground(null);
            quit.setPadding(new Insets(0));
            // Button backToMenu = new Button("Back to Menu");
            // backToMenu.setBackground(null);
            // backToMenu.setPadding(new Insets(0));

            VBox winScreenLayout = new VBox();
            BackgroundImage BI = new BackgroundImage(youWin, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background BG = new Background(BI);
            winScreenLayout.setBackground(BG);

            Label youWonText = new Label("You won :D");
            Font youWonFont = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 40);
            youWonText.setFont(youWonFont);

            quit.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });
            quit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    quit.setText("Quit Game <");
                }
            });
            quit.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    quit.setText("Quit Game");
                }
            });
            // backToMenu.setOnAction(new EventHandler<ActionEvent>() {
            //     public void handle(ActionEvent actionEvent){
            //         stage.getScene().setRoot(menuRoot);
            //     }
            // });

            winScreenLayout.setAlignment(Pos.BOTTOM_LEFT);
            winScreenLayout.setPadding(new Insets(80));
            winScreenLayout.getChildren().addAll(youWonText, quit);
            winScreenLayout.setSpacing(30 * factor);

            stage.getScene().setRoot(winScreenLayout);
        }
    }

    public static void checkGameOver(){
        if(Game.checkGameOver()){
            Button quit = new Button("Quit Game");
            quit.setBackground(null);
            quit.setPadding(new Insets(0));
            // Button backToMenu = new Button("Back to Menu");
            // backToMenu.setBackground(null);
            // backToMenu.setPadding(new Insets(0));

            VBox lossScreenLayout = new VBox();
            lossScreenLayout.setId("lossRoot");
            Label youLostText = new Label("You lost :(");
            Font youLostFont = Font.loadFont("file:Resources/Fonts/Press-Gothic-W01-Regular.ttf", 40);
            youLostText.setFont(youLostFont);

            quit.setOnAction(new EventHandler<ActionEvent>() {
                public void handle(ActionEvent actionEvent) {
                    Platform.exit();
                }
            });
            quit.setOnMouseEntered(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    quit.setText("> Quit Game");
                }
            });
            quit.setOnMouseExited(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent mouseEvent) {
                    quit.setText("Quit Game");
                }
            });
            // backToMenu.setOnAction(new EventHandler<ActionEvent>() {
            //     public void handle(ActionEvent actionEvent){
            //         stage.getScene().setRoot(menuRoot);
            //     }
            // });

            lossScreenLayout.setAlignment(Pos.BOTTOM_RIGHT);
            lossScreenLayout.setPadding(new Insets(80));
            lossScreenLayout.getChildren().addAll(youLostText, quit);
            lossScreenLayout.setSpacing(30 * factor);

            stage.getScene().setRoot(lossScreenLayout);
        }
    }
}