package com.example.banking_sys_with_mongo.exception;

public class UserException extends RuntimeException
{

    String code;
    String message;
    public UserException(ExceptionType type)
    {
        super(type.message);
        this.code = type.code;
        this.message = type.message;
    }
}
