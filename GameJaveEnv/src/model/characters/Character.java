package model.characters;
import java.awt.Point;

import model.world.CharacterCell;
import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughActionsException;

//read = getters, write = setters
//Description : A class representing the Characters available in the game. No objects of type Character can be instantiated.
public abstract class Character {
    private String name; //read only
    private Point location; 
    private int maxHp; //read only
    private int currentHp;
    private int attackDmg; //read only
    private Character target;

    public Character(String name, int maxHp, int attackDmg) {
        this.name = name;
        this.maxHp = maxHp;
        this.attackDmg = attackDmg;
        currentHp = maxHp;
    }

    //read and write
    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;   
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
    	if(currentHp < 0)
            this.currentHp = 0;
        else if(currentHp > maxHp)
            this.currentHp = maxHp;
        else
            this.currentHp = currentHp;
    }

    public Character getTarget() {
        return target;
    }
    
    public void setTarget(Character target) {
        this.target = target;
    }

    //read only
    public String getName() {
        return name;
    }
    
    public int getMaxHp() {
        return maxHp;
    }
    
    public int getAttackDmg() {
        return attackDmg;
    }
    
    
    
    public void attack() throws NotEnoughActionsException, InvalidTargetException {
    	if(((target.getLocation().y + 1 == location.y || target.getLocation().y - 1 == location.y) && target.getLocation().x == location.x) 
    			|| ((target.getLocation().x + 1 == location.x || target.getLocation().x - 1 == location.x) && target.getLocation().y == location.y)) {
	    	target.setCurrentHp(target.getCurrentHp() - attackDmg);
	    	target.setTarget(this);
	    	if(target.getCurrentHp() <= 0)
	    		target.onCharacterDeath();
    	}
    	else
    		throw new InvalidTargetException("Selected target is invalid.");
    }
    
    public void defend(Character c) {
    	target.setCurrentHp(target.getCurrentHp() - (attackDmg / 2));
    	if(target.getCurrentHp() <= 0)
    		target.onCharacterDeath();
    }
    
    public void onCharacterDeath() {
    	Game.map[14 - location.y][location.x] = new CharacterCell(null);
    }
}
