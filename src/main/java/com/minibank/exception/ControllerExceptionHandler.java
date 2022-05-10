package com.minibank.exception;

import java.util.Arrays;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler({
            RuntimeException.class
        })
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        new Date(),
        Arrays.asList(ex.getMessage()),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            ResourceNotFoundException.class
    })
    public ResponseEntity<ErrorMessage> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                Arrays.asList(ex.getMessage()),
                request.getDescription(false));

        return new ResponseEntity<ErrorMessage>(message, HttpStatus.NOT_FOUND);
    }
    
        @ExceptionHandler({
            MethodArgumentTypeMismatchException.class
    })
        public ResponseEntity<ErrorMessage> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex,
                WebRequest request) {
            ErrorMessage message = new ErrorMessage(
                    HttpStatus.BAD_REQUEST.value(),
                    new Date(),
                    Arrays.asList(ex.getMessage()),
                    request.getDescription(false));

            return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
        }
    
    @ExceptionHandler({
            KamuKorupsiException.class
    })
    public ResponseEntity<ErrorMessage> kamuKorupsiException(KamuKorupsiException ex, WebRequest request) {
    ErrorMessage message = new ErrorMessage(
        HttpStatus.NOT_ACCEPTABLE.value(),
        new Date(),
        Arrays.asList(ex.getMessage()),
        request.getDescription(false));
    
    return new ResponseEntity<ErrorMessage>(message, HttpStatus.BAD_REQUEST);
    }
}