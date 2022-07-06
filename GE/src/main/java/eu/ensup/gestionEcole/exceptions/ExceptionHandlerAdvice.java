package eu.ensup.gestionEcole.exceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> HandleUnknownExceptions(Exception exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @ExceptionHandler(UserEmailNotFoundException.class)
    public ResponseEntity<String> HandleUserEmailNotFoundExceptions(UserEmailNotFoundException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NO_CONTENT);
        return entity;
    }

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<String> HandleTokenExpiredExceptions(TokenExpiredException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        return entity;
    }

    @ExceptionHandler(NonValidJWTTokenException.class)
    public ResponseEntity<String> HandleNonValidJwtTokenExceptions(NonValidJWTTokenException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        return entity;
    }


    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> HandleExpiredJwtTokenExceptions(ExpiredJwtException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.FORBIDDEN);
        return entity;
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> HandleIOExceptions(IOException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return entity;
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> HandleProductNotFOundExceptions(ProductNotFoundException exception){
        ResponseEntity<String> entity = new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
        return entity;
    }

}
