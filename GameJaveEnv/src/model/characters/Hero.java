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
		if(getTarget() instanceof Zombie){
			if(this instanceof Fighter && specialAction) {
				super.attack();
			}
			else if(actionsAvailable > 0){
					super.attack();
					actionsAvailable--;
			}
			else
				throw new NotEnoughActionsException("Character has no more available actions.");
		}
		else
			throw new InvalidTargetException("Selected target is invalid");
    }
    
    /*public void onCharacterDeath() {
    	super.onCharacterDeath();
    	//Game.heroes.remove(this);
		//Game.map[getLocation().x][getLocation().y].setVisible(true);
    	Game.map[14 - getLocation().y][getLocation().x].setVisible(false);
    	if(getLocation().y < 14)
    		Game.map[13 - getLocation().y][getLocation().x].setVisible(false);
    	if(getLocation().y > 0)
    		Game.map[15 - getLocation().y][getLocation().x].setVisible(false);
    	if(getLocation().x > 0)
    		Game.map[14 - getLocation().y][getLocation().x - 1].setVisible(false);
    	if(getLocation().x < 14)
    		Game.map[14 - getLocation().y][getLocation().x + 1].setVisible(false);
	}*/
    
    public void move(Direction d) throws MovementException, NotEnoughActionsException{ // still need to update visibility
    	if(actionsAvailable < 1) { //Guarded Clause
			throw new NotEnoughActionsException("Character has no more available actions.");
		}
		int newY =  getLocation().y;
		int newX = getLocation().x;
		switch (d)
		{
			case DOWN: 
				newX--;
				break;
			case LEFT:
				newY--;
				break;
			case RIGHT:
				newY++;
				break;
			case UP:
				newX++;
				break;
			default:
				break;
			
		}
		if (newX < 0 || newX > 14 || newY < 0 || newY > 14) // ensure Movement within board bounds of 15x15 interlinked
		{
			throw new MovementException("Movement Out of Bounds");
		}
		if ((Game.map[newX][newY] instanceof CharacterCell)) 
		{
			if (((CharacterCell)Game.map[newX][newY]).getCharacter() != null)
			{
				throw new MovementException("Cell is occupied");
			}
		} 
		else if (!(Game.map[newX][newY] instanceof TrapCell) && !(Game.map[newX][newY] instanceof CollectibleCell))
		{
			throw new MovementException("Target is not a valid cell");
		}
		
		moveHelper(Game.map[newX][newY]);
		Game.map[newX][newY] = new CharacterCell(this);
		Game.map[getLocation().x][getLocation().y] = new CharacterCell(null);
		setLocation(new Point(newX,newY));
		if(getCurrentHp() <= 0)
			onCharacterDeath();
		else
			Game.updateVisibility();

    }
    
    public void moveHelper(Cell targetCell) { 
    	actionsAvailable--;
    	if(targetCell instanceof CollectibleCell) {
			if(((CollectibleCell) targetCell).getCollectible() instanceof Vaccine)
				vaccineInventory.add((Vaccine)(((CollectibleCell) targetCell).getCollectible()));
			else
				supplyInventory.add((Supply)(((CollectibleCell) targetCell).getCollectible()));
		}
		else if(targetCell instanceof TrapCell){
			setCurrentHp(getCurrentHp() - ((TrapCell) targetCell).getTrapDamage());
		}
    }
    
    public void useSpecial() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
    	if(actionsAvailable > 0 || this instanceof Fighter)  {
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
    if (getTarget() == null)
	{
		throw new InvalidTargetException("No Target Selected");
	}
	{	
		if(actionsAvailable > 0) {
			if(getTarget() instanceof Zombie && Game.checkAdjacent(this, getTarget())) {
				actionsAvailable--;
				vaccineInventory.get(0).use(this);
			}
			else throw new InvalidTargetException("Can only cure zombies in adjacent cells.");
		}
		else
			throw new NotEnoughActionsException("Character has no more available acitons.");
	}
}
}
