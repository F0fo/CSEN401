package engine;
import java.util.ArrayList;

import model.characters.*;
import model.characters.Character;
import model.collectibles.Supply;
import model.collectibles.Vaccine;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

import java.awt.Point;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NotEnoughActionsException;
   
public class Game {
    public static ArrayList<Hero> availableHeroes;
    public static ArrayList<Hero> heroes;
    public static ArrayList<Zombie> zombies;
    public static Cell[][] map;

    public static void loadHeroes(String filePath) throws IOException, FileNotFoundException {
        FileReader fr = new FileReader(filePath);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        
        while(line != null) {
            String[] lineSplit = line.split(",");
            String type = lineSplit[1];
            switch(type) {
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
    
    public static void startGame(Hero h) throws MovementException {
		
    }
    
    //public static boolean checkWin() {
    	
    //}
    
    //public static boolean checkGameOver() {
    	
    //}
    
    public static void endTurn() throws NotEnoughActionsException, InvalidTargetException, MovementException {
		
    }

	public static void updateVisibility() {
    	
    }

	//public static boolean checkAdjacent(Character c1, Character c2){
		
	//}

	public static void selectTarget(Zombie z) {
		
    }

	public static void spawnZombie(){
		
	}
    
   public static void main(String[] args) throws IOException, FileNotFoundException, MovementException {
    	availableHeroes = new ArrayList<Hero>();
		Game.loadHeroes("D:\\University\\Semester 4\\Computer Programming Lab\\Game\\Milestone 1\\Heroes CSV File\\Heros.csv"); // path. hmm
		
    	startGame(availableHeroes.get(0));
    }
    
    public static void printMap(){
    	for(int i = 14; i >= 0; i--){
    		for(int j = 0; j < 15; j++){
    			if(map[i][j] == null)
    				System.out.print("[ ]");
    			else{
    				if(map[i][j] instanceof CharacterCell && ((CharacterCell)map[i][j]).getCharacter() != null){
    					if(((CharacterCell)map[i][j]).getCharacter() instanceof Hero)
    						System.out.print("[H]");
    					else if(((CharacterCell)map[i][j]).getCharacter() instanceof Zombie)
    						System.out.print("[Z]");
    				}
    				else if(map[i][j] instanceof CollectibleCell){
    					if(((CollectibleCell) map[i][j]).getCollectible() instanceof Vaccine)
    						System.out.print("[V]");
    					else if(((CollectibleCell) map[i][j]).getCollectible() instanceof Supply)
    						System.out.print("[S]");
    				}
    				else if(map[i][j] instanceof TrapCell){
    					System.out.print("[T]");
    				}
    				else
    					System.out.print("[ ]");
    			}
				/*if(map[i][j] instanceof CharacterCell && ((CharacterCell)map[i][j]).isVisible())
					System.out.print("[V]");
				else
					System.out.print("[ ]");*/
    		}
    		System.out.println();
    	}
    }
}
