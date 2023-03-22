package engine;
import java.util.ArrayList;

import model.characters.*;
import model.world.Cell;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
   
public class Game {

    public static ArrayList<Hero> availableHeroes;
    public static ArrayList<Hero> heroes;
    public static ArrayList<Zombie> zombies;
    public static Cell[][] map;

    public static void loadHeroes(String filePath) throws IOException, FileNotFoundException
    {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        availableHeroes = new ArrayList<Hero>();
        String line = br.readLine();
        while(line != null){
            String[] lineSplit = line.split(",");
            String type = lineSplit[1];
            switch(type){
                case("FIGH"):
                    Fighter f = new Fighter(lineSplit[0], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[3]));
                    availableHeroes.add(f);
                    break;
                case("MED"):
                    Medic m = new Medic(lineSplit[0], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[3]));
                    availableHeroes.add(m);
                    break;
                case("EXP"):
                    Explorer e = new Explorer(lineSplit[0], Integer.parseInt(lineSplit[2]), Integer.parseInt(lineSplit[4]), Integer.parseInt(lineSplit[3]));
                    availableHeroes.add(e);
                    break;
            }
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
