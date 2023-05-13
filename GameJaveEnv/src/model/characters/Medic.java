package model.characters;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;

public class Medic extends Hero {
    public Medic(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg, maxActions);
    }
    
    public void useSpecial() throws NoAvailableResourcesException, InvalidTargetException {
    	if(getTarget() instanceof Hero && Game.checkAdjacent(this, getTarget())){
			super.useSpecial();
    		getTarget().setCurrentHp(getMaxHp());
    	}
    	else
    		throw new InvalidTargetException("Can only heal other heroes.");
    }
    
}
