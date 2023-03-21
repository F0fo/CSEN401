package model.world;

public abstract class Cell {
    private boolean isVisible;
    
    public Cell(){
    
    }

    public boolean isVisible(){
        return isVisible;
    }

    public void setIsVisible(boolean isVisible){
        this.isVisible = isVisible;
    }
}
