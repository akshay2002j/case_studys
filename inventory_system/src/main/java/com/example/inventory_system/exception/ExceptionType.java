package com.example.inventory_system.exception;

public enum ExceptionType {
    PRODUCT_NOT_FOUND("PNF","Product Not Found"),
    PRODUCT_ALREADY_EXISTS("PAE","Product Already Exists"),
    PRODUCT_NOT_ENOUGH("PNF","Product Not Enough"),
    PRODUCT_ALREADY_DELETED("PAD","Product Already Deleted"),
    PRODUCT_NOT_ENOUGH_QUANTITY("PNEQ","Product Not Enough Quantity"),
    SUPPLIER_NOT_FOUND("SNF","Supplier Not Found"),
    SUPPLIER_ALREADY_EXISTS("SAE","Supplier Already Exists"),
    DB_EXCEPTION("DB","Database Exception"),;

    final String code;
    final String message;
    ExceptionType(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
