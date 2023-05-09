package model.characters;
import model.collectibles.Vaccine;

import java.awt.Point;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import model.collectibles.Supply;
import model.world.Cell;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
//No objects of type Hero can be instantiated.
//read = getters, write = setters

public abstract class Hero extends Character {
    private int actionsAvailable;
    private int maxActions; // read only
    private boolean specialAction;
    private ArrayList<Vaccine> vaccineInventory; //read only
    private ArrayList<Supply> supplyInventory; //read only

    public Hero(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg);
        this.maxActions = maxActions;
        actionsAvailable = maxActions;
        vaccineInventory = new ArrayList<Vaccine>();
        supplyInventory = new ArrayList<Supply>();
    }

    //read & write
    public int getActionsAvailable() {
        return actionsAvailable;
    }

    public void setActionsAvailable(int actionsAvailable) {
        this.actionsAvailable = actionsAvailable;
    }

    public boolean isSpecialAction() {
        return specialAction;
    }

    public void setSpecialAction(boolean specialAction) {
        this.specialAction = specialAction;
    }

    //read only
    public int getMaxActions() {
    	return maxActions;
    }
    
    public ArrayList<Vaccine> getVaccineInventory() {
        return vaccineInventory;
    }

    public ArrayList<Supply> getSupplyInventory() {
        return supplyInventory;
    }
    
    
    
    public void attack() throws NotEnoughActionsException, InvalidTargetException {
    	if(this instanceof Fighter && specialAction) {
    		if(getTarget() instanceof Zombie)
    			super.attack();
    		else
    			throw new InvalidTargetException("Selected target is invalid.");
    	}
    	else if(actionsAvailable > 0){
    		if(getTarget() instanceof Zombie){
	    		super.attack();
	    		actionsAvailable--;
    		}
    		else
    			throw new InvalidTargetException("Selected target is invalid.");
    	}
    	else
    		throw new NotEnoughActionsException("Character has no more available actions.");
    }
    
    public void onCharacterDeath() {
    	super.onCharacterDeath();
    	Game.heroes.remove(this);
    	Game.map[14 - getLocation().y][getLocation().x].setVisible(false);
    	if(getLocation().y < 14)
    		Game.map[13 - getLocation().y][getLocation().x].setVisible(false);
    	if(getLocation().y > 0)
    		Game.map[15 - getLocation().y][getLocation().x].setVisible(false);
    	if(getLocation().x > 0)
    		Game.map[14 - getLocation().y][getLocation().x - 1].setVisible(false);
    	if(getLocation().x < 14)
    		Game.map[14 - getLocation().y][getLocation().x + 1].setVisible(false);
    }
    
    public void move(Direction d) throws MovementException, NotEnoughActionsException{ // still need to update visibility
    	if(actionsAvailable > 0) {
	    	if(d == Direction.UP) {
	    		if(getLocation().y < 14 && ((Game.map[13 - getLocation().y][getLocation().x] instanceof CharacterCell)
	    				&& ((CharacterCell)Game.map[13 - getLocation().y][getLocation().x]).getCharacter() == null)
	    				|| !(Game.map[13 - getLocation().y][getLocation().x] instanceof CharacterCell)) {
	    			moveHelper(Game.map[13 - getLocation().y][getLocation().x]);
	    			Game.map[13 - getLocation().y][getLocation().x] = new CharacterCell(this);
	    			Game.map[14 - getLocation().y][getLocation().x] = new CharacterCell(null);
	    			setLocation(new Point(getLocation().x, getLocation().y + 1));
	    		}
	    		else
	    			throw new MovementException("Character cannot move up.");
	    	}
	    	
	    	if(d == Direction.DOWN){
	    		if(getLocation().y > 0 && ((Game.map[15 - getLocation().y][getLocation().x] instanceof CharacterCell)
	    				&& ((CharacterCell)Game.map[15- getLocation().y][getLocation().x]).getCharacter() == null)
	    				|| !(Game.map[15 - getLocation().y][getLocation().x] instanceof CharacterCell)) {
	    			moveHelper(Game.map[15 - getLocation().y][getLocation().x]);
	    			Game.map[15 - getLocation().y][getLocation().x] = new CharacterCell(this);
	    			Game.map[14 - getLocation().y][getLocation().x] = new CharacterCell(null);
	    			setLocation(new Point(getLocation().x, getLocation().y - 1));
	    		}
	    		else
	    			throw new MovementException("Character cannot move down.");
	    	}
	    	
	    	if(d == Direction.LEFT) {
	    		if(getLocation().x > 0 && ((Game.map[14 - getLocation().y][getLocation().x - 1] instanceof CharacterCell)
	    				&& ((CharacterCell)Game.map[14 - getLocation().y][getLocation().x - 1]).getCharacter() == null)
	    				|| !(Game.map[14 - getLocation().y][getLocation().x - 1] instanceof CharacterCell)) {
	    			moveHelper(Game.map[14 - getLocation().y][getLocation().x - 1]);
	    			Game.map[14 - getLocation().y][getLocation().x - 1] = new CharacterCell(this);
	    			Game.map[14 - getLocation().y][getLocation().x] = new CharacterCell(null);
	    			setLocation(new Point(getLocation().x - 1, getLocation().y));
	    		}
	    		else
	    			throw new MovementException("Character cannot move left.");
	    	}
	    	
	    	if(d == Direction.RIGHT){
	    		if(getLocation().x < 14 && ((Game.map[14 - getLocation().y][getLocation().x + 1] instanceof CharacterCell)
	    				&& ((CharacterCell)Game.map[14 - getLocation().y][getLocation().x + 1]).getCharacter() == null)
	    				|| !(Game.map[14 - getLocation().y][getLocation().x + 1] instanceof CharacterCell)) {
	    			moveHelper(Game.map[14 - getLocation().y][getLocation().x + 1]);
	    			Game.map[14 - getLocation().y][getLocation().x + 1] = new CharacterCell(this);
	    			Game.map[14 - getLocation().y][getLocation().x] = new CharacterCell(null);
	    			setLocation(new Point(getLocation().x + 1, getLocation().y));
	    		}
	    		else
	    			throw new MovementException("Character cannot move right.");
	    	}
	    	Game.updateVisibility();
    	}
    	else
    		throw new NotEnoughActionsException("Character has no more available actions.");
    }
    
    public void moveHelper(Cell targetCell) {
    	actionsAvailable--;
    	if(targetCell instanceof CollectibleCell) {
			if(((CollectibleCell) targetCell).getCollectible() instanceof Vaccine)
				vaccineInventory.add((Vaccine)(((CollectibleCell) targetCell).getCollectible()));
			else
				supplyInventory.add((Supply)(((CollectibleCell) targetCell).getCollectible()));
		}
		else if(targetCell instanceof TrapCell)
			setCurrentHp(getCurrentHp() - ((TrapCell) targetCell).getTrapDamage());
    }
    
    public void useSpecial() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
    	if(actionsAvailable > 0) {
    		if(!supplyInventory.isEmpty()) {
    			actionsAvailable--;
    			supplyInventory.remove(0);
    			specialAction = true;
    		}
    		else
    			throw new NoAvailableResourcesException("Character does not have any supplies.");
    	}
    	else
    		throw new NotEnoughActionsException("Character has no more available actions.");
    		
    }
    
    public void cure() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
    	if(actionsAvailable > 0) {
    		if(getTarget() instanceof Zombie && ((getTarget().getLocation().y + 1 == getLocation().y || getTarget().getLocation().y - 1 == 
    				getLocation().y) && getTarget().getLocation().x == getLocation().x) || ((getTarget().getLocation().x + 1 == getLocation().x ||
    				getTarget().getLocation().x - 1 == getLocation().x) && getTarget().getLocation().y == getLocation().y)) {
	    		if(!vaccineInventory.isEmpty()) {
	    			actionsAvailable--;
	    			vaccineInventory.remove(0);
	    			int y = getTarget().getLocation().y;
	    			int x = getTarget().getLocation().x;
	    			int r = (int)(Math.random() * (Game.availableHeroes.size() + 1));
	    			Game.map[14 - y][x] = new CharacterCell(Game.availableHeroes.get(r));
	    			Game.availableHeroes.remove(r);
	    			// safety check?
	    			((CharacterCell)Game.map[14 - y][x]).getCharacter().setLocation(new Point(x, y));
	    		}
	    		else
	    			throw new NoAvailableResourcesException("Character does not have any vaccines.");
    		}
    		else throw new InvalidTargetException("Can only cure zombies in adjacent cells.");
    	}
    	else
    		throw new NotEnoughActionsException("Character has no more available acitons.");
    }
}
