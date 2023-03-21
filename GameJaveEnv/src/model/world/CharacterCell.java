package model.world;

public class CharacterCell extends Cell {
    private Character character;
    private boolean isSafe;
    
    public CharacterCell(Character character){
        super();
        this.character = character;
    }
    
    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void setIsSafe(boolean isSafe){
        this.isSafe = isSafe;
    }

    public boolean isSafe(){
        return isSafe;
    }
}
