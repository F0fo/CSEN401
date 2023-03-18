package model.world;

public class CharacterCell extends Cell {
    private Character character;
    private boolean isSafe;

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setIsSafe(boolean isSafe){
        this.isSafe = isSafe;
    }

    public Character getCharacter() {
        return character;
    }

    public boolean isIsSafe(){
        return isSafe;
    }

    public CharacterCell(Character character, boolean isSafe){
        super();
        this.character = character;
        this.isSafe = isSafe;
    }
}
