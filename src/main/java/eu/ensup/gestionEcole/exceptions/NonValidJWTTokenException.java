package eu.ensup.gestionEcole.exceptions;

public class NonValidJWTTokenException extends RuntimeException {
    public NonValidJWTTokenException(String message) {
        super(message);
    }
}
