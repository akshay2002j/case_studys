package com.example.inventory_system.exception;

public class SupplierException extends RuntimeException {


   public SupplierException(ExceptionType type) {
        super(type.message);
    }
}
