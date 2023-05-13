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
		if(h.getVaccineInventory().size() <= 0)
			throw new NoAvailableResourcesException("No available vaccines");

		// h.getVaccineInventory().remove(this);

		// Game.zombies.remove(h.getTarget());

		// int x = h.getTarget().getLocation().x;
		// int y = h.getTarget().getLocation().y;

		// int r = (int)(Math.random() * (Game.availableHeroes.size()));
		// Hero newHero = Game.availableHeroes.get(r);
		// Game.map[x][y] = new CharacterCell(newHero);
		// Game.heroes.add(newHero);
		// Game.availableHeroes.remove(newHero);
		// Game.updateVisibility();
		// newHero.setLocation(new Point(x, y));

		h.getVaccineInventory().remove(0);
		Game.zombies.remove(h.getTarget());
		int y = h.getTarget().getLocation().y;
		int x = h.getTarget().getLocation().x;
		int r = (int)(Math.random() * (Game.availableHeroes.size()));
		Game.map[x][y] = new CharacterCell(Game.availableHeroes.get(r));
		Game.heroes.add(Game.availableHeroes.get(r));
		Game.availableHeroes.remove(r);
		((CharacterCell)Game.map[x][y]).getCharacter().setLocation(new Point(x, y));
	}
}
