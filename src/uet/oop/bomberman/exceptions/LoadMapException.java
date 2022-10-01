package uet.oop.bomberman.exceptions;

public class LoadMapException extends GameException {

    private static final String messageAlert = "Can not find the level specification file.";

    public LoadMapException() {
        super();
        System.err.println(messageAlert);
    }

    public LoadMapException(String message) {
        super(message);
    }

    public LoadMapException(Throwable cause) {
        super(cause);
        System.err.println(messageAlert);
    }

    public LoadMapException(String message, Throwable cause) {
        super(message, cause);
    }
}
