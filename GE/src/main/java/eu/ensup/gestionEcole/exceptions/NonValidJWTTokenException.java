package eu.ensup.gestionEcole.exceptions;

/**
 * The type Non valid jwt token exception.
 */
public class NonValidJWTTokenException extends RuntimeException {
    /**
     * Instantiates a new Non valid jwt token exception.
     *
     * @param message the message
     */
    public NonValidJWTTokenException(String message) {
        super(message);
    }
}
