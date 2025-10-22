package com.example.banking_sys_with_mongo.exception;

public enum ExceptionType {

    USER_NOT_FOUND("U404", "Account not found"),
    USER_ALREADY_EXISTS("U406", "Account already exists"),
    ACCOUNT_NOT_FOUND("AC404", "Account not found"),
    ACCOUNT_ALREADY_EXISTS("AC406", "Account already exists"),
    INSUFFICIENT_BALANCE("AC400", "Insufficient balance"),
    BD_ERROR("B500", "Database error"),
    ;


    final String code;
    final String message;
    ExceptionType(String code , String message) {
        this.code = code;
        this.message = message;
    }

}
