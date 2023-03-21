package exceptions;

public class MovementException extends GameActionException{
    // occurs when a character tries to make an invalid movement
    public MovementException(){

    }

    public MovementException(String s){
        super(s);
    }
}
