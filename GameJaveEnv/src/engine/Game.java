package engine;
import java.util.ArrayList;

import model.characters.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//import java.util.Scanner;    
import model.world.Cell;
public class Game {

    public static ArrayList<Hero> availableHeros;
    public static ArrayList<Hero> heroes;
    public static ArrayList<Zombie> zombies;
    public static Cell[][] map;

    public static Hero h;

    public static void loadHeroes(String filePath) throws IOException, FileNotFoundException
    {
        
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        availableHeros = new ArrayList<Hero>();
        String line = br.readLine();
        while(line != null){
            String[] lineSplit = line.split(",");
            String type = lineSplit[1];
            switch(type){
                case("FIGH"):
                    h = new Fighter(lineSplit[0], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[3]));
                case("MED"):
                    h = new Medic(lineSplit[0], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[3]));
                case("EXP"):
                    h = new Explorer(lineSplit[0], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[3]));
            }
            availableHeros.add(h);
            line = br.readLine();
        }
        br.close();
    }

    /*public static void main(String[] args){
        try {
            loadHeroes("C:\\Users\\Farah\\Desktop\\Heros.csv"); // change path if ur gonna test
            for(int i = 0; i < availableHeros.size(); i++) {   
                System.out.println(availableHeros.get(i).getName() + " " + availableHeros.get(i).getMaxHp() + " " + availableHeros.get(i).getMaxActions() + " " + availableHeros.get(i).getAttackDmg());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
