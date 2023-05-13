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
		
    }
    
    public void onCharacterDeath() {
    	
	}
    
    public void move(Direction d) throws MovementException, NotEnoughActionsException{ // still need to update visibility

    }
    
    
    public void useSpecial() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
    	
    		
    }
    
    public void cure() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
		
	}
}
