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
    public static Cell[][] map = new Cell[15][15];

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

		for(int i = 0; i < 15; i++) {
			for(int j = 0; j < 15; j++) {
				map[i][j] = null;
			}
		}
		map[0][0]=new CharacterCell(h);

    	// safety check?
    	
    	availableHeroes.remove(h);
    	heroes = new ArrayList<Hero>();
    	heroes.add(h);
    	h.setLocation(new Point(0, 0));
    	
    	zombies = new ArrayList<Zombie>();
    	
    	for(int i = 0; i < 25; i++) {
    		int r = (int)(Math.random() * 15);
    		int c = (int)(Math.random() * 15);
    		while(map[r][c] != null) {
    			r = (int)(Math.random() * 15);
        		c = (int)(Math.random() * 15);
    		}
    		if(i < 5) {
    			Vaccine v = new Vaccine();
    			map[r][c] = new CollectibleCell(v);
    		}
    		else if(i < 10) {
    			Supply s = new Supply();
        		map[r][c] = new CollectibleCell(s);
    		}
    		else if(i < 15) {
    			map[r][c] = new TrapCell();
    		}
    		else {
    			Zombie z = new Zombie();
    			z.setLocation(new Point(r, c));
    			map[r][c] = new CharacterCell(z);
    			zombies.add(z);
    		}
    	}
    	
    	for(int i = 0; i < 15; i++) {
    		for(int j = 0; j < 15; j++) {
    			if(map[i][j] == null)
    				map[i][j] = new CharacterCell(null);
    		}
    	}
    	
    	updateVisibility();
    }
    
    public static boolean checkWin() {
    	for(int i = 0; i < 15; i++) {
    		for(int j = 0; j < 15; j++) {
    			if(map[i][j] instanceof CollectibleCell) {
    				if(((CollectibleCell)map[i][j]).getCollectible() instanceof Vaccine)
    					return false;
    			}
    		} 
    	}
    	if(heroes.size() < 5)
    		return false;
    	for(int i = 0; i < heroes.size(); i++) {
    		if(!(heroes.get(i).getVaccineInventory().isEmpty()))
    			return false;
    	}
    	return true;
    }
    
    public static boolean checkGameOver() {
    	for(int i = 0; i < 15; i++) {
    		for(int j = 0; j < 15; j++) {
    			if(map[i][j] instanceof CollectibleCell) {
    				if(((CollectibleCell)map[i][j]).getCollectible() instanceof Vaccine)
    					return false;
    			}
    		} 
    	}
    	for(int i = 0; i < heroes.size(); i++) {
    		if(!(heroes.get(i).getVaccineInventory().isEmpty()))
    			return false;
    	}
    	return true;
    }
    
    public static void endTurn() throws NotEnoughActionsException, InvalidTargetException, MovementException {
		for(int i = 0; i < heroes.size(); i++){
			Hero h = heroes.get(i);
			h.setTarget(null);
			h.setActionsAvailable(h.getMaxActions());
			h.setSpecialAction(false);
		}

		for(int i = 0; i < zombies.size(); i++){
			Zombie z = zombies.get(i);
			selectTarget(z);
			if(z.getTarget() != null)
				z.attack();
			z.setTarget(null);
    	}
    	
    	spawnZombie();

		updateVisibility();
    }

	public static boolean checkAdjacent(Character c1, Character c2){
		Point l1 = c1.getLocation();
		Point l2 = c2.getLocation();
		if((l2.x - 1 == l1.x || l2.x + 1 == l1.x) && (l1.y == l2.y || (l2.y - 1 == l1.y || l2.y + 1 == l1.y)))
			return true;
		if((l2.y - 1 == l1.y || l2.y + 1 == l1.y) && (l1.x == l2.x || (l2.x - 1 == l1.x || l2.x + 1 == l1.x)))
			return true;
		return false;
	}

	public static void selectTarget(Zombie z) {
		int x = z.getLocation().x;
		int y = z.getLocation().y;
		for(int i = x - 1; i <= x + 1; i++){
			for(int j = y - 1; j <= y + 1; j++){
				if(i >= 0 && i < 15 && j >= 0 && j < 15){
					if(map[i][j] instanceof CharacterCell && ((CharacterCell)map[i][j]).getCharacter() instanceof Hero){
						z.setTarget(((CharacterCell)map[i][j]).getCharacter());
						return;
					}
				}
			}
		}
		z.setTarget(null);
    }

	public static void spawnZombie(){
		int r = (int)(Math.random() * 15);
    	int c = (int)(Math.random() * 15);
    	while(!(Game.map[r][c] instanceof CharacterCell && ((CharacterCell)Game.map[r][c]).getCharacter() == null)) {
    		r = (int)(Math.random() * 15);
        	c = (int)(Math.random() * 15);
    	}
    	Zombie z = new Zombie();
    	z.setLocation(new Point(r, c));
    	Game.map[r][c] = new CharacterCell(z);
    	Game.zombies.add(z);
	}
    
    public static void updateVisibility() {
		for(int i = 0; i < 15; i++) {
    		for(int j = 0; j < 15; j++) {
				for (int n = -1; n < 2 ; n++)
				{
					if (i + n < 0 || i + n > 14 )
						continue;
					for (int m = -1; m < 2 ; m++) 
					{
						if (j + m < 0 || j + m > 14 )
							continue;
						if(!(map[i][j] instanceof CharacterCell && ((CharacterCell)map[i][j]).getCharacter() instanceof Hero))
							continue;
						map[i+n][m+j].setVisible(true);
					}
				}
    		}
    	}
    }
    
   public static void main(String[] args) throws IOException, FileNotFoundException, MovementException {
    	availableHeroes = new ArrayList<Hero>();
		Game.loadHeroes("D:\\University\\Semester 4\\Computer Programming Lab\\Game\\Milestone 1\\Heroes CSV File\\Heros.csv"); // path. hmm
		
    	startGame(availableHeroes.get(0));
		
    	printMap(map);

		zombies.get(0).onCharacterDeath();

		//System.out.println("============================");

		printMap(map);

		System.out.println(zombies.size());
    }
    
    public static void printMap(Cell[][] map){
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
			System.out.print(i);
    		System.out.println();
    	}
    }
}
