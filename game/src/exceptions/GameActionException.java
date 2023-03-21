package exceptions;

abstract class GameActionException extends Exception{

    GameActionException(){
        GameActionException x = new GameActionException();
    }

    GameActionException(String s){
        GameActionException x = new GameActionException(s);
    }
}
