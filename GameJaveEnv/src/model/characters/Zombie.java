package model.characters;

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
        Game.zombies.remove(this);
		Game.spawnZombie();
    }
}
