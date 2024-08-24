package com.example.task.controllers;

import com.example.task.exceptions.GeneralException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Log4j2
public class ExceptionController {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<?> generalException(GeneralException e)
    {
        log.error("Exception: ", e);
        return new ResponseEntity<>(e.getExceptionData(), HttpStatus.BAD_REQUEST);
    }
}
