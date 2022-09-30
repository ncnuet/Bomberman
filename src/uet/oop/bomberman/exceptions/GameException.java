package uet.oop.bomberman.exceptions;

public class GameException extends Exception {
    private static final String messageAlert = "Game is discrete.";
    public GameException() {
        super();
        System.err.println(messageAlert);
    }

    public GameException(String message) {
        super(message);
    }

    public GameException(Throwable cause) {
        super(cause);
        System.err.println(messageAlert);
    }

    public GameException(String message, Throwable cause) {
        super(message, cause);
    }
}
