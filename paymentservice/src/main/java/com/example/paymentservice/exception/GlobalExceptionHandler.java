package com.example.paymentservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(value = NullPointerException.class)
    public  ResponseEntity<?> handleException(NullPointerException e){
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<?> handleUserNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @ExceptionHandler(value = TransactionException.class)
    public ResponseEntity<?> handleUserNotFoundException(TransactionException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Transaction not found");
    }

}
