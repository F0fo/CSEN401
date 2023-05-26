package views;

import java.awt.Point;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.characters.Direction;
import model.characters.Zombie;
import model.characters.Hero;
import model.world.CharacterCell;
import model.world.TrapCell;
import model.characters.Character;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


public class UserInputs {
    public static Zombie selectedZombie;

    public static EventHandler<MouseEvent> mouseHandler = new EventHandler<MouseEvent>() {

        public void handle(MouseEvent m) { 
            
            StackPane s;
            Point p;
            switch(m.getButton())
            {
                case PRIMARY:
                s = (StackPane)((Button)m.getSource()).getParent();
                p = findStackPane(s);
                if(Game.map[p.x][p.y] instanceof CharacterCell && ((CharacterCell)(Game.map[p.x][p.y])).getCharacter() instanceof Hero){
                    Main.selectedChar = (Hero)((CharacterCell)(Game.map[p.x][p.y])).getCharacter();
                    StatsManager.updateSelectedStats();
                    StatsManager.updateOtherStats();

                    Board.targetBorder(Board.mapGrid);
                    Board.selectionBorder(Board.mapGrid);
                }
                break;

                case SECONDARY:
                s = (StackPane)((Button)m.getSource()).getParent();
                p = findStackPane(s);
                if(Game.map[p.x][p.y] instanceof CharacterCell && Game.map[p.x][p.y].isVisible()){
                    Main.selectedChar.setTarget(((CharacterCell)(Game.map[p.x][p.y])).getCharacter());
                    
                    Board.selectionBorder(Board.mapGrid);
                    Board.targetBorder(Board.mapGrid);
                }
                break;


                default:
                break;
            } 
        } 
     };

     public static EventHandler<KeyEvent> keyHandler = new EventHandler<KeyEvent>() {
        public void handle(KeyEvent e){
            KeyCode keyCode = e.getCode();

            switch(keyCode)
            {
                case W:
                    try {
                        int oldX = (int) Main.selectedChar.getLocation().getX();
                        int oldY = (int) Main.selectedChar.getLocation().getY();
                        Boolean trap = false;
                        if(oldX + 1 < 15 && Game.map[oldX + 1][oldY] instanceof TrapCell){
                            trap = true;
                        }

                        Main.selectedChar.move(Direction.UP);
                        Main.exceptionLabel.setText("");

                        int X = (int) Main.selectedChar.getLocation().getX() - 1;
                        int Y = (int) Main.selectedChar.getLocation().getY();
                        if(trap)
                            trapWarning();
                        StackPane s = Board.getStackPane(Y, 14-X);
                        if(s.getChildren().get(1) instanceof ImageView)
                            s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);
                        StatsManager.updateSelectedStats();

                        Board.makeVisible(Board.mapGrid);

                        Board.characterRemove(Board.mapGrid);

                        Board.selectionBorder(Board.mapGrid);
                        Board.targetBorder(Board.mapGrid);
                        
                    } catch (MovementException | NotEnoughActionsException e1) {
                        Main.exceptionLabel.setText(e1.getMessage());
                    }
                    break;

                case A:
                    try {
                        int oldX = (int) Main.selectedChar.getLocation().getX();
                        int oldY = (int) Main.selectedChar.getLocation().getY();
                        Boolean trap = false;
                        if(oldY - 1 >= 0 && Game.map[oldX][oldY - 1] instanceof TrapCell){
                            trap = true;
                        }

                        Main.selectedChar.move(Direction.LEFT);
                        Main.exceptionLabel.setText("");

                        int X = (int) Main.selectedChar.getLocation().getX();
                        int Y = (int) Main.selectedChar.getLocation().getY() + 1;
                        if(trap)
                            trapWarning();
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        if(s.getChildren().get(1) instanceof ImageView)
                            s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);

                        StatsManager.updateSelectedStats();

                        Board.makeVisible(Board.mapGrid);

                        Board.characterRemove(Board.mapGrid);

                        Board.selectionBorder(Board.mapGrid);
                        Board.targetBorder(Board.mapGrid);

                    } catch (MovementException | NotEnoughActionsException e1) {
                        Main.exceptionLabel.setText(e1.getMessage());
                    }
                    break;

                case S:
                    try {
                        int oldX = (int) Main.selectedChar.getLocation().getX();
                        int oldY = (int) Main.selectedChar.getLocation().getY();
                        Boolean trap = false;
                        if(oldX - 1 >= 0 && Game.map[oldX - 1][oldY] instanceof TrapCell){
                            trap = true;
                        }
                        
                        Main.selectedChar.move(Direction.DOWN);
                        Main.exceptionLabel.setText("");
                        Main.checkGameOver();

                        int X = (int) Main.selectedChar.getLocation().getX() + 1;
                        int Y = (int) Main.selectedChar.getLocation().getY();
                        if(trap)
                            trapWarning();
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        if(s.getChildren().get(1) instanceof ImageView)
                            s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);

                        StatsManager.updateSelectedStats();

                        Board.makeVisible(Board.mapGrid);

                        Board.characterRemove(Board.mapGrid);

                        Board.selectionBorder(Board.mapGrid);
                        Board.targetBorder(Board.mapGrid);

                    } catch (MovementException | NotEnoughActionsException e1) {
                            Main.exceptionLabel.setText(e1.getMessage());
                    }
                    break;
                
                    
                case D:
                    try {
                        int oldX = (int) Main.selectedChar.getLocation().getX();
                        int oldY = (int) Main.selectedChar.getLocation().getY();
                        Boolean trap = false;
                        if(oldY + 1 < 15 && Game.map[oldX][oldY + 1] instanceof TrapCell){
                            trap = true;
                        }

                        Main.selectedChar.move(Direction.RIGHT);
                        Main.exceptionLabel.setText("");
                        
                        int X = (int) Main.selectedChar.getLocation().getX();
                        int Y = (int) Main.selectedChar.getLocation().getY() - 1;
                        if(trap)
                            trapWarning();
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        if(s.getChildren().get(1) instanceof ImageView)
                        s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);
                        
                        StatsManager.updateSelectedStats();
                        
                        Board.makeVisible(Board.mapGrid);
                        
                        Board.characterRemove(Board.mapGrid);

                        Board.selectionBorder(Board.mapGrid);
                        Board.targetBorder(Board.mapGrid);
                        
                    } catch (MovementException | NotEnoughActionsException e1) {
                        Main.exceptionLabel.setText(e1.getMessage());
                    }
                    break;
                    
                case E:
                    try {
                        Game.endTurn();
                        Main.exceptionLabel.setText("");
                        Board.characterRemove(Board.mapGrid);
                        Board.zombieAdd(Board.mapGrid);

                        StatsManager.clearStats();
                        StatsManager.updateSelectedStats();
                        StatsManager.updateOtherStats();

                        Board.makeInvisible(Board.mapGrid);
                        Board.makeVisible(Board.mapGrid);

                        Board.targetBorder(Board.mapGrid);
                        Board.selectionBorder(Board.mapGrid);

                        Main.checkGameOver();

                    } catch (NotEnoughActionsException | InvalidTargetException e1) {
                        Main.exceptionLabel.setText(e1.getMessage());
                    }
                    break;
                    
                case C:
                    try {
                        Main.selectedChar.cure();
                        Main.exceptionLabel.setText("");
                        Board.heroManager(Board.mapGrid);
                        Main.selectedChar.setTarget(null);
                        
                        StatsManager.clearStats();
                        StatsManager.updateSelectedStats();
                        StatsManager.updateOtherStats();
                        
                        Board.targetBorder(Board.mapGrid);
                        Board.selectionBorder(Board.mapGrid);
                        Main.checkWin(false);

                    } catch (NoAvailableResourcesException | InvalidTargetException | NotEnoughActionsException e1) {
                        Main.exceptionLabel.setText(e1.getMessage());
                    }
                    break;
                case X:
                    try 
                    {
                        Main.selectedChar.attack();
                        Main.exceptionLabel.setText("");

                        Board.zombieAdd(Board.mapGrid);
                        Board.characterRemove(Board.mapGrid);

                        StatsManager.updateSelectedStats();

                        Board.targetBorder(Board.mapGrid);
                        Board.selectionBorder(Board.mapGrid);

                    } catch ( InvalidTargetException | NotEnoughActionsException e1)
                    {
                        Main.exceptionLabel.setText(e1.getMessage());
                    }
                    break;
                case Q:
                try 
                    {
                        Main.selectedChar.useSpecial();
                        Main.exceptionLabel.setText("");
                        Board.makeVisible(Board.mapGrid);
                        
                        StatsManager.clearStats();
                        StatsManager.updateSelectedStats();
                        StatsManager.updateOtherStats();

                        Board.selectionBorder(Board.mapGrid);
                        Board.targetBorder(Board.mapGrid);

                    } catch (InvalidTargetException | NoAvailableResourcesException e1)
                    {
                        Main.exceptionLabel.setText(e1.getMessage());
                    }
                    break;
                
                case P:
                    Main.checkWin(true);
                    break;

                default:
                    break;
            }

            
        }
     };

    public static Point findStackPane(StackPane s){
        for(Node node: Board.mapGrid.getChildren()){
            if(node.equals(s)){
                return new Point(14 - GridPane.getRowIndex(node), GridPane.getColumnIndex(node));
            }
        }
        return null;
    }

    private static void trapWarning()  //not detecting trap cell properly [HERE] 
    {

        // Alert alert = new Alert(AlertType.WARNING);
        // alert.setTitle("Trap Warning");
        // alert.setHeaderText("Player Stepped on a Trap!");
        // alert.setContentText("Be careful!");

        // // Add a custom button to close the alert
        // ButtonType closeButton = new ButtonType("Close");
        // alert.getButtonTypes().setAll(closeButton);

        // // Show the alert and wait for user interaction
        // alert.showAndWait();

        Main.exceptionLabel.setText("You walked into a trap!");
        
    }

}
