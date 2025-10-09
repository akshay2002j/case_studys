package com.example.banking_sys_with_mongo.exception;

public class AccountException extends RuntimeException
{
    public AccountException(String message)
    {
        super(message);
    }
}
