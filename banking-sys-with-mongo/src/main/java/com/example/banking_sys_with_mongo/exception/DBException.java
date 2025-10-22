package com.example.banking_sys_with_mongo.exception;

public class DBException extends RuntimeException {

    String code;
    String message;
    public DBException(ExceptionType exceptionType)
    {
        super(exceptionType.message);
        this.code = exceptionType.code;
        this.message = exceptionType.message;
    }

}
