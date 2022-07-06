package eu.ensup.gestionEcole.exceptions;

/**
 * The type Token expired exception.
 */
public class TokenExpiredException extends RuntimeException {
    /**
     * Instantiates a new Token expired exception.
     *
     * @param message the message
     */
    public TokenExpiredException(String message) {
        super(message);
    }
}
