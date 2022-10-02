package uet.oop.bomberman.exceptions;

public class AudioNotReadyException extends GameException {
    private static final String messageAlert = "Audio not available yet.";

    public AudioNotReadyException() {
        super();
        System.err.println(messageAlert);
    }

    public AudioNotReadyException(String message) {
        super(message);
    }

    public AudioNotReadyException(Throwable cause) {
        super(cause);
        System.err.println(messageAlert);
    }

    public AudioNotReadyException(String message, Throwable cause) {
        super(message, cause);
    }
}
