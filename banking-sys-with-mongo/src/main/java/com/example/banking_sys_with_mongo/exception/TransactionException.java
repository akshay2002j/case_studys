package com.example.banking_sys_with_mongo.exception;

public class TransactionException extends RuntimeException{

    String code;
    String message;


    public TransactionException(String code, String message)
    {
        super(message);
        this.code = code;
        this.message = message;
    }
}
