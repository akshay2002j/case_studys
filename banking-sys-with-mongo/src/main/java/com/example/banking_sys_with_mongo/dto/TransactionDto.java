package com.example.banking_sys_with_mongo.dto;

import com.example.banking_sys_with_mongo.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionDto {
     String id;
    String accountNumber;
    BigDecimal amount;
    TransactionType type;
    String toAccountNumber;
    String fromAccountNumber;
    LocalDateTime transactionDate;
}
