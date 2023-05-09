package model.characters;
import java.awt.Point;

//read = getters, write = setters
//Description : A class representing the Characters available in the game. No objects of type Character can be instantiated.
public abstract class Character {
    
    private String name; //readonly
    private Point location; 
    private int maxHp; //readOnly
    private int currentHp;
    private int attackDmg; //readonly
    private Character target;

    public Character(String name, int maxHp, int attackDmg)
    {
            this.name = name;
            this.maxHp = maxHp;
            this.attackDmg = attackDmg;
    }

    //read and write
    public Point getLocation() 
    {
        return location;
    }

    public void setLocation(Point location) 
    {
        this.location = location;   
    }

    public int getCurrentHp() 
    {
        return currentHp;
    }

    public void setCurrentHp(int currentHp) 
    {
        
        this.currentHp = (currentHp > maxHp)? maxHp : currentHp;
    }

    public Character getTarget() 
    {
        return target;
    }
    
    public void setTarget(Character target) 
    {
        this.target = target;
    }


//read only
    public int getAttackDmg()
     {
        return attackDmg;
    }

    public int getMaxHp()
    {
        return maxHp;
    }

    public String getName() 
    {
        return name;
    }
}
