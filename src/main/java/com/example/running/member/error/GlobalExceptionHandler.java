package com.example.running.member.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NoSuchElementException.class)
    public ResponseEntity handleNoSuchElementException (NoSuchElementException e){
        ErrorResponse response = new ErrorResponse(ErrorStatus.NO_SUCH_ELEMENT);
        return new ResponseEntity (response, HttpStatus.BAD_REQUEST);
    }
}
