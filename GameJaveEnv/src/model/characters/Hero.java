package model.characters;
import model.collectibles.Vaccine;
import java.util.ArrayList;
import model.collectibles.Supply;
//No objects of type Hero can be instantiated.
//read = getters, write = setters


public abstract class Hero extends Character 
{

    //read = get, write = setters

    private int actionsAvailable;
    private int maxActions;
    private boolean specialAction;
    private ArrayList<Vaccine> vaccineInventory; //readonly
    private ArrayList<Supply> supplyInventory; //readonly
    //read & write
    public void setActionsAvailable(int actionsAvailable) 
    {
        this.actionsAvailable = actionsAvailable;
    }
    public void setMaxActions(int maxActions) 
    {
        this.maxActions = maxActions;
    }
    public void setSpecialAction(boolean specialAction) 
    {
        this.specialAction = specialAction;
    }
    public int getActionsAvailable() 
    {
        return actionsAvailable;
    }
    public int getMaxActions() 
    {
        return maxActions;
    }
   public boolean isSpecialAction() {
       return specialAction;
   }
    //read only
    public ArrayList<Vaccine> getVaccineInventory() 
    {
        return vaccineInventory;
    }
    public ArrayList<Supply> getSupplyInventory() 
    {
        return supplyInventory;
    }
    
    public Hero(String name, int maxHp, int attackDmg, int maxActions) 
    {
        super(name, maxHp, attackDmg);
        this.maxActions = maxActions;
        this.actionsAvailable = maxActions;
        this.setCurrentHp(maxHp);
        vaccineInventory = new ArrayList<Vaccine>();
        supplyInventory = new ArrayList<Supply>();
    }

}
