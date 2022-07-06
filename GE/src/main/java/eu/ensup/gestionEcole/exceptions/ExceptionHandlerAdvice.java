package eu.ensup.gestionEcole.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

/**
 * The type Exception handler advice.
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {

    /**
     * Handle unknown exceptions response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> HandleUnknownExceptions(Exception exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    /**
     * Handle user email not found exceptions response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<String> HandleUserEmailNotFoundExceptions(UserEmailNotFoundException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
        return entity;
    }

    /**
     * Handle token expired exceptions response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> HandleTokenExpiredExceptions(TokenExpiredException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        return entity;
    }

    /**
     * Handle non valid jwt token exceptions response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(NonValidJWTTokenException.class)
    public ResponseEntity<String> HandleNonValidJwtTokenExceptions(NonValidJWTTokenException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        return entity;
    }


    /**
     * Handle expired jwt token exceptions response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> HandleExpiredJwtTokenExceptions(ExpiredJwtException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        return entity;
    }

    /**
     * Handle io exceptions response entity.
     *
     * @param exception the exception
     * @return the response entity
     */
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> HandleIOExceptions(IOException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }
}
