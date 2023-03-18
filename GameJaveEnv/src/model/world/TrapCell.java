package model.world;

public class TrapCell extends Cell{
    private int trapDamage;

    public int getTrapDamage() {
        return trapDamage;
    }

    public TrapCell(){
        super();
        trapDamage = (int)((Math.random() * 3) + 1) * 10; // range = n to n + m - 1
    }
}
