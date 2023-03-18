package model.world;

public abstract class Cell {
    private boolean isVisible;

    public boolean isIsVisible(){
        return isVisible;
    }

    public void setIsVisible(boolean isVisible){
        this.isVisible = isVisible;
    }

    public Cell(){

    }
}
