package exceptions;

public class InvalidTargetException extends GameActionException {
    // occurs when trying to target a wrong character with an action
    public InvalidTargetException() {

    }

    public InvalidTargetException(String s) {
        super(s);
    }
}
