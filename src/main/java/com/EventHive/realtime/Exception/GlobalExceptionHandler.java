package com.EventHive.realtime.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(VenueNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVenueNotFound(VenueNotFoundException ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEventNotFound(EventNotFoundException ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(InvalidEventStateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEventState(InvalidEventStateException ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(InvalidDateTimeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDateTime(InvalidDateTimeException ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(InvalidEventDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEventData(InvalidEventDataException ex){
        ErrorResponse error=new ErrorResponse(ex.getMessage(),HttpStatus.NOT_FOUND.value(),LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }     
}
