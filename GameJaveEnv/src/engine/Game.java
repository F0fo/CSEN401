package engine;
import java.util.ArrayList;
import model.characters.Hero;
import model.characters.Zombie;
import java.io.*;  
import java.util.Scanner;    
//import model.world.Cell;
public class Game {

    static ArrayList<Hero> availableHeros;
    static ArrayList<Hero> heros;
    static ArrayList<Zombie> zombies;
    //static Cell [][] map;

    public static void loadHeros(String filePath)
    {
        Scanner sc = new Scanner(filePath);  
        sc.useDelimiter(",");   //sets the delimiter pattern  
        while (sc.hasNext())  //returns a boolean value     
        {  
        
        }   
        sc.close();
    }
}
