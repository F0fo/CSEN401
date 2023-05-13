package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Medic extends Hero {
    public Medic(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg, maxActions);
    }
    
    public void useSpecial() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
		
    }
    
}
