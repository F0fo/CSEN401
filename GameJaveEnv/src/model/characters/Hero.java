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
        if(this instanceof Fighter && specialAction){
            super.attack();
            return;
        }
		if(actionsAvailable > 0){
            if(getTarget() instanceof Zombie){
                super.attack();
                actionsAvailable--;
            }
            else
                throw new InvalidTargetException("Can only attack zombies.");
        }
        else
            throw new NotEnoughActionsException("No actions available.");
    }
    
    public void onCharacterDeath() {
    	super.onCharacterDeath();
        Game.heroes.remove(this);

        boolean hideCells = true;

        for(int i = 0; i < Game.heroes.size(); i++){
            if(Game.heroes.get(i) instanceof Explorer && Game.heroes.get(i).isSpecialAction())
                hideCells = false;
        }

        if(hideCells){
            int x = getLocation().x;
            int y = getLocation().y;

            for(int i = x - 1; i <= x + 1; i++){
                for(int j = y - 1; j < y + 1; j++){
                    if(i >= 0 && i < 15 && j >= 0 && j < 15){
                        Game.map[i][j].setVisible(false);
                    }
                }
            }
        }

        Game.updateVisibility();
	}
    
    public void move(Direction d) throws MovementException, NotEnoughActionsException{
        if(getActionsAvailable() <= 0)
            throw new NotEnoughActionsException("No actions available.");

        int newX = getLocation().x;
        int newY = getLocation().y;

        switch(d){
            case UP:
                newX++;
                break;
            case DOWN:
                newX--;
                break;
            case RIGHT:
                newY++;
                break;
            case LEFT:
                newY--;
                break;
        }

        if(newX < 0 || newX > 14 || newY < 0 || newY > 14)
            throw new MovementException("Out of map bounds.");
        if(Game.map[newX][newY] instanceof CharacterCell && ((CharacterCell)Game.map[newX][newY]).getCharacter() != null)
            throw new MovementException("Cell is occupied.");

        actionsAvailable--;
        
        Game.map[getLocation().x][getLocation().y] = new CharacterCell(null);
    
        if(Game.map[newX][newY] instanceof CollectibleCell)
            ((CollectibleCell)Game.map[newX][newY]).getCollectible().pickUp(this);
        else if (Game.map[newX][newY] instanceof TrapCell){
            int dmg = ((TrapCell)Game.map[newX][newY]).getTrapDamage();
            setCurrentHp(getCurrentHp() - dmg);
            if(getCurrentHp() <= 0)
                onCharacterDeath();
        }

        Game.map[newX][newY] = new CharacterCell(this);
        setLocation(new Point(newX, newY));
        Game.updateVisibility();
    }
    
    
    public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
        if(getSupplyInventory().size() <= 0)
            throw new NoAvailableResourcesException("No supplies available.");

    	getSupplyInventory().get(0).use(this);
        setSpecialAction(true);
    }
    
    public void cure() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
		if(actionsAvailable <= 0)
            throw new NotEnoughActionsException("No actions available.");
        if(getVaccineInventory().size() == 0)
            throw new NoAvailableResourcesException("No vaccines available.");
        if(!(getTarget() instanceof Zombie && Game.checkAdjacent(this, getTarget())))
            throw new InvalidTargetException("Can only cure zombies in adjacent cells.");
        
        actionsAvailable--;
        getVaccineInventory().get(0).use(this);
	}
}
