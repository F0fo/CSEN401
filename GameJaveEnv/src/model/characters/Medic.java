package model.characters;

import exceptions.InvalidTargetException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;

public class Medic extends Hero {
    public Medic(String name, int maxHp, int attackDmg, int maxActions) {
        super(name, maxHp, attackDmg, maxActions);
    }
    
    public void useSpecial() throws NotEnoughActionsException, NoAvailableResourcesException, InvalidTargetException {
    	super.useSpecial(); // do something about only being able to heal one character per supply idfk
    	if(getTarget() instanceof Hero){
    		if(getTarget().getCurrentHp() != getTarget().getMaxHp()) {
    			getTarget().setCurrentHp(getMaxHp());
    			setSpecialAction(false);
    		}
    		else
    			throw new InvalidTargetException("Hero is already at max health.");
    	}
    	else
    		throw new InvalidTargetException("Can only heal other heroes.");
    }
    
}
