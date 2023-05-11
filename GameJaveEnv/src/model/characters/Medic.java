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
		 // do something about only being able to heal one character per supply idfk
    	if(getTarget() instanceof Hero && Game.checkAdjacent(this, getTarget())){
    		getTarget().setCurrentHp(getMaxHp());
			super.useSpecial();
    	}
    	else
    		throw new InvalidTargetException("Can only heal other heroes.");
    }
    
}
