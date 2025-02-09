package com.videostream.videoapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex){

        HashMap<String, String> errorDetails = new HashMap<>();
        errorDetails.put("message", ex.getMessage());

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


}
