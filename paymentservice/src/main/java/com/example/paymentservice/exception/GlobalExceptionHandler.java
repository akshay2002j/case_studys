package com.example.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleException(Exception e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(value = RuntimeException.class)
    public  ResponseEntity<?> handleException(Exception e, HttpStatus status){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    @ExceptionHandler(value = NullPointerException.class)
    public  ResponseEntity<?> handleException(NullPointerException e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    public  ResponseEntity<?> handleException(IllegalArgumentException e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @ExceptionHandler(value = TransactionNotFound.class)
    public ResponseEntity<?> handleUserNotFoundException(TransactionNotFound e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
    }

}
