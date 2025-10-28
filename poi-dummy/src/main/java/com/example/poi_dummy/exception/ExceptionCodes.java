package com.example.poi_dummy.exception;

public enum ExceptionCodes {
    WRONG_HEADERS("EXL001","Wrong Headers in Excel file"),
    INVALID_EXCEL_FILE("EXL002","Invalid Excel file"),

    ;

    final String code;
    final String message;
    ExceptionCodes(String code, String message) {
    this.code = code;
    this.message = message;
    }
}
