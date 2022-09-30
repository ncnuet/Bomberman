package uet.oop.bomberman.exceptions;

public class ParseMapException extends LoadMapException {
    private static final String messageAlert = "Level specification file is incorrect form.";
    public ParseMapException() {
        super();
        System.err.println(messageAlert);
    }

    public ParseMapException(String message) {
        super(message);
    }

    public ParseMapException(Throwable cause) {
        super(cause);
        System.err.println(messageAlert);
    }

    public ParseMapException(String message, Throwable cause) {
        super(message, cause);
    }
}
