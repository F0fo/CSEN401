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
       // if ((location.getY() < 0) || (location.getY() > 14) || (location.getX())= < 0) || location.getX() > 14)  )
        this.location = location;   
        
    }

    public int getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) {
    	if(currentHp <= 0)
        {
            this.currentHp = 0;
            //onCharacterDeath();
        }
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
        if(this instanceof Zombie && getTarget() == null){
            Game.selectTarget((Zombie)this);
        }
        if(Game.checkAdjacent(this, getTarget())){
	    	target.setCurrentHp(target.getCurrentHp() - attackDmg);
            target.setTarget(this);
	    	defend(target);
	    	if(target.getCurrentHp() <= 0)
	    		target.onCharacterDeath();
        }
        else
            throw new InvalidTargetException("Selected target is invalid.");
    }
    
    public void defend(Character c) {
    	c.target.setCurrentHp(c.target.getCurrentHp() - (c.attackDmg / 2));
    	if(c.target.getCurrentHp() <= 0)
    		c.target.onCharacterDeath();
    }
    
    public void onCharacterDeath() {
    	Game.map[location.x][location.y] = new CharacterCell(null);
        if(this instanceof Zombie){
            Game.zombies.remove(this);
            
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
        else
            Game.heroes.remove(this);
    }
}
