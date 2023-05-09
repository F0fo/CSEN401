package model.characters;

import java.awt.Point;

import model.world.CharacterCell;
import engine.Game;

public class Zombie extends Character {
    private static int ZOMBIES_COUNT = 0;
    private final static int MAXHP = 40;
    private final static int DAMAGE = 10;
    
    public Zombie() {
        super("Zombie " + (++ZOMBIES_COUNT), MAXHP, DAMAGE);
    }
    
    public void onCharacterDeath() {
    	super.onCharacterDeath();
    	int r = (int)(Math.random() * 15);
    	int c = (int)(Math.random() * 15);
    	while(!(Game.map[r][c] instanceof CharacterCell && ((CharacterCell)Game.map[r][c]).getCharacter() == null)) {
    		r = (int)(Math.random() * 15);
        	c = (int)(Math.random() * 15);
    	}
    	Zombie z = new Zombie();
    	z.setLocation(new Point(14 - r, c));
    	Game.map[r][c] = new CharacterCell(z);
    	// check safety?
    	Game.zombies.add(z);
    }
}
