package model.collectibles;

import java.awt.Point;

import engine.Game;
import exceptions.NoAvailableResourcesException;
import model.characters.Hero;
import model.world.CharacterCell;

public class Vaccine implements Collectible {
    public Vaccine() {

    }

	public void pickUp(Hero h) {
		h.getVaccineInventory().add(this);
	}

	public void use(Hero h) throws NoAvailableResourcesException {
		
	}
}
