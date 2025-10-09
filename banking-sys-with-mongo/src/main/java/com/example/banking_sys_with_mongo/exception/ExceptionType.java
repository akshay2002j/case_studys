package com.example.banking_sys_with_mongo.exception;

public enum ExceptionType {

    USER_NOT_FOUND("U404", "Account not found"),
    USER_ALREADY_EXISTS("U406", "Account already exists"),;

    ExceptionType(String code , String message) {

    }

}
