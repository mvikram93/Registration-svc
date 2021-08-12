package com.alchemy.registration.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class UserException {

    @ExceptionHandler(value = UserExistException.class)
    public ResponseEntity<Object> exception(UserExistException exception){
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<Object> UserNotFound(UserNotFoundException e){
        UserExceptionHandler exception = new UserExceptionHandler(e.getMessage(), HttpStatus.BAD_REQUEST, LocalDate.now());
        System.out.println("Status Code: "+HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

}
