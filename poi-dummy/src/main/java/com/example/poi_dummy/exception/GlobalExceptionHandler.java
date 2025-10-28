package com.example.poi_dummy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExcelException.class)
    public ResponseEntity<?> handleException(ExcelException excelException) {
        return new ResponseEntity<>(excelException.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
