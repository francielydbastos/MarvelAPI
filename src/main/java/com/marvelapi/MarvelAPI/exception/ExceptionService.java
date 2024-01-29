package com.marvelapi.MarvelAPI.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionService {
    @ExceptionHandler(CharacterNotFoundException.class)
    ResponseEntity<CharacterNotFoundException> handleException(CharacterNotFoundException err){
        UserErrorResponse uer = new UserErrorResponse();
        uer.setStatus(HttpStatus.NOT_FOUND.value());
        uer.setMessage("No character found with the provided ID.");
        return new ResponseEntity(uer, HttpStatus.NOT_FOUND);
    }

}
