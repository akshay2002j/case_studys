package com.example.paymentservice.exception;

public class TransactionNotFound extends RuntimeException {

   public TransactionNotFound(String message){
        super(message);
    }
}
