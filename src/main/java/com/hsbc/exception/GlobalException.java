package com.hsbc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(value
            = { IllegalArgumentException.class, IllegalStateException.class })
    public ResponseEntity badrequest(RuntimeException ex, WebRequest request){
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDate.now());
        return new ResponseEntity(exceptionMessage,  HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity exception(RuntimeException ex, WebRequest request){
        ExceptionMessage exceptionMessage = new ExceptionMessage(ex.getMessage(), HttpStatus.BAD_REQUEST, LocalDate.now());
        return new ResponseEntity(exceptionMessage,  HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
