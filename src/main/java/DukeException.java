/**
 * Custom exception class for Duke application.
 */
public class DukeException extends Exception {
    /**
     * Constructs a DukeException with the specified message.
     *
     * @param message The error message associated with the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}