package model.characters;

public class Zombie extends Character{
    private static int ZOMBIES_COUNT;

    public Zombie(){
        super("Zombie", 40, 10);
    }

    public static void setZombiesCount(int zombiesCount) {
        ZOMBIES_COUNT = zombiesCount;
    }

    public static int getZombiesCount() {
        return ZOMBIES_COUNT;
    }

}
