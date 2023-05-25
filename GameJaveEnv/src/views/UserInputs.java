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
                }
                break;

                case SECONDARY:
                s = (StackPane)((Button)m.getSource()).getParent();
                p = findStackPane(s);
                if(Game.map[p.x][p.y] instanceof CharacterCell){
                    Main.selectedChar.setTarget(((CharacterCell)(Game.map[p.x][p.y])).getCharacter());
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
                        Main.selectedChar.move(Direction.UP);

                        int X = (int) Main.selectedChar.getLocation().getX() - 1;
                        int Y = (int) Main.selectedChar.getLocation().getY();
                        trapWarning(X,Y);
                        StackPane s = Board.getStackPane(Y, 14-X);
                        if(s.getChildren().get(1) instanceof ImageView)
                            s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);
                        StatsManager.updateSelectedStats();

                        Board.makeVisible(Board.mapGrid);

                        Board.characterRemove(Board.mapGrid);
                        
                    } catch (MovementException | NotEnoughActionsException e1) {
                        e1.printStackTrace();
                    }
                    break;

                case A:
                    try {
                        Main.selectedChar.move(Direction.LEFT);

                        int X = (int) Main.selectedChar.getLocation().getX();
                        int Y = (int) Main.selectedChar.getLocation().getY() + 1;
                        trapWarning(X,Y);
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        if(s.getChildren().get(1) instanceof ImageView)
                            s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);

                        StatsManager.updateSelectedStats();

                        Board.makeVisible(Board.mapGrid);

                        Board.characterRemove(Board.mapGrid);

                    } catch (MovementException | NotEnoughActionsException e1) {
                        e1.printStackTrace();
                    }
                    break;

                case S:
                    try {
                        Main.selectedChar.move(Direction.DOWN);

                        int X = (int) Main.selectedChar.getLocation().getX() + 1;
                        int Y = (int) Main.selectedChar.getLocation().getY();
                        trapWarning(X,Y);
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        if(s.getChildren().get(1) instanceof ImageView)
                            s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);

                        StatsManager.updateSelectedStats();

                        Board.makeVisible(Board.mapGrid);

                        Board.characterRemove(Board.mapGrid);

                    } catch (MovementException | NotEnoughActionsException e1) {
                        e1.printStackTrace();
                    }
                    break;
                
                    
                    case D:
                    try {
                        Main.selectedChar.move(Direction.RIGHT);
                        
                        int X = (int) Main.selectedChar.getLocation().getX();
                        int Y = (int) Main.selectedChar.getLocation().getY() - 1;
                        trapWarning(X,Y);
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        if(s.getChildren().get(1) instanceof ImageView)
                        s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);
                        
                        StatsManager.updateSelectedStats();
                        
                        Board.makeVisible(Board.mapGrid);
                        
                        Board.characterRemove(Board.mapGrid);
                        
                    } catch (MovementException | NotEnoughActionsException e1) {
                        e1.printStackTrace();
                    }
                    break;
                    
                case E:
                    try {
                        Game.endTurn();
                        Board.characterRemove(Board.mapGrid);
                        Board.zombieAdd(Board.mapGrid);

                        // StatsManager.clearStats();
                        // StatsManager.updateSelectedStats();
                        // StatsManager.updateOtherStats();

                        Board.makeInvisible(Board.mapGrid);
                        Board.makeVisible(Board.mapGrid);

                    } catch (NotEnoughActionsException | InvalidTargetException e1) {
                        e1.printStackTrace();
                    }
                    break;
                    
                case C:
                    try {
                        Main.selectedChar.cure();
                        Board.heroManager(Board.mapGrid);

                        StatsManager.clearStats();
                        StatsManager.updateSelectedStats();
                        StatsManager.updateOtherStats();

                    } catch (NoAvailableResourcesException | InvalidTargetException | NotEnoughActionsException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case R:
                    try 
                    {
                        Main.selectedChar.attack();

                        Board.zombieAdd(Board.mapGrid);
                        Board.characterRemove(Board.mapGrid);

                    } catch ( InvalidTargetException | NotEnoughActionsException e1)
                    {
                        e1.printStackTrace();
                    }
                    break;
                case T:
                try 
                    {
                        Main.selectedChar.useSpecial();
                        Board.makeVisible(Board.mapGrid);
                        
                        StatsManager.clearStats();
                        StatsManager.updateSelectedStats();
                        StatsManager.updateOtherStats();

                                                            //need to test fighter special after fixing attack 
                    } catch (InvalidTargetException | NoAvailableResourcesException e1)
                    {
                        e1.printStackTrace();
                    }
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

    private static void trapWarning(int x, int y)  //not detecting trap cell properly [HERE] 
    {

        // if ( !(Game.map[x][y] instanceof TrapCell) )
        //     return;
        // Alert alert = new Alert(AlertType.WARNING);
        // alert.setTitle("Trap Warning");
        // alert.setHeaderText("Player Stepped on a Trap!");
        // alert.setContentText("Be careful!");

        // // Add a custom button to close the alert
        // ButtonType closeButton = new ButtonType("Close");
        // alert.getButtonTypes().setAll(closeButton);

        // // Show the alert and wait for user interaction
        // alert.showAndWait();
        
    }

}
