package com.example.inventory_system.exception;

public class DBException extends RuntimeException{

    public DBException(ExceptionType type){
        super(type.message);
    }
}
