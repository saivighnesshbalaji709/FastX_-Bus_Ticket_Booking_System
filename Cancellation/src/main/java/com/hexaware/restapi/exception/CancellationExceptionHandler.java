package com.hexaware.restapi.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

public class CancellationExceptionHandler {
	@ExceptionHandler(CancellationNotFoundException.class)
    public ResponseEntity<String> handleNotFound(CancellationNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}



