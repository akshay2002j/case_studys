package com.example.poi_dummy.exception;

public class ExcelException extends RuntimeException{


    ExceptionCodes code;

    public ExcelException(ExceptionCodes code) {
        super(code.message);
        this.code = code;
    }
}
