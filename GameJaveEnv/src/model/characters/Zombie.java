package model.characters;

import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

public class Zombie extends Character {
    private static int ZOMBIES_COUNT = 0;
    private final static int MAXHP = 40;
    private final static int DAMAGE = 10;
    
    public Zombie() {
        super("Zombie " + (++ZOMBIES_COUNT), MAXHP, DAMAGE);
    }

    public void attack() throws InvalidTargetException, NotEnoughActionsException{
        ArrayList<Hero> possibleTargets = Game.selectTarget(this);
        if(possibleTargets.size() == 0)
            return;
        setTarget(possibleTargets.get(0));
        super.attack();
    }
    
    public void onCharacterDeath() {
    	super.onCharacterDeath();
        Game.zombies.remove(this);
        Game.spawnZombie();
    }
}
