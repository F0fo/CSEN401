package exceptions;

public class NoAvailableResourcesException extends GameActionException{
    // occurs when a character tries to use a collectible they don't have
    public NoAvailableResourcesException(){

    }

    public NoAvailableResourcesException(String s){
        super(s);
    }
}
