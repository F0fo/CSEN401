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
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import model.characters.Direction;
import model.characters.Hero;
import model.world.CharacterCell;

public class UserInputs {
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
                System.out.print("Other");
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
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);
                        
                        StatsManager.updateSelectedStats();
                        
                    } catch (MovementException | NotEnoughActionsException e1) {
                        e1.printStackTrace();
                    }
                    break;

                case A:
                    try {
                        Main.selectedChar.move(Direction.LEFT);

                        int X = (int) Main.selectedChar.getLocation().getX();
                        int Y = (int) Main.selectedChar.getLocation().getY() + 1;
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);

                        StatsManager.updateSelectedStats();

                    } catch (MovementException | NotEnoughActionsException e1) {
                        e1.printStackTrace();
                    }
                    break;

                case S:
                    try {
                        Main.selectedChar.move(Direction.DOWN);

                        int X = (int) Main.selectedChar.getLocation().getX() + 1;
                        int Y = (int) Main.selectedChar.getLocation().getY();
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);

                        StatsManager.updateSelectedStats();

                    } catch (MovementException | NotEnoughActionsException e1) {
                        e1.printStackTrace();
                    }
                    break;

                case D:
                    try {
                        Main.selectedChar.move(Direction.RIGHT);

                        int X = (int) Main.selectedChar.getLocation().getX();
                        int Y = (int) Main.selectedChar.getLocation().getY() - 1;
                        StackPane s = Board.getStackPane(Y, 14 - X);
                        s.getChildren().remove(1);
                        Board.mapGrid = Board.heroManager(Board.mapGrid);

                        StatsManager.updateSelectedStats();

                    } catch (MovementException | NotEnoughActionsException e1) {
                        e1.printStackTrace();
                    }
                    break;
                
                case C:
                    try {
                        Main.selectedChar.cure();
                        Board.heroManager(Board.mapGrid);
                    } catch (NoAvailableResourcesException | InvalidTargetException | NotEnoughActionsException e1) {
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
}
