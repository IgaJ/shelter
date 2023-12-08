package com.example.shelter.controller;

import com.example.shelter.service.AnimalServiceException;
import com.example.shelter.service.BoxServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(BoxServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleBoxServiceException(BoxServiceException ex) {
        String response = ex.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AnimalServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<String> handleAnimalServiceException(AnimalServiceException ex) {
        String response = ex.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    //application.properties: server.error.include-stacktrace=never

}
