package exceptions;

public class NotEnoughActionsException extends GameActionException {
    // occurs when a character tries to take an action without the sufficient action points available
    public NotEnoughActionsException() {

    }

    public NotEnoughActionsException(String s) {
        super(s);
    }
}
