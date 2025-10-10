package com.example.banking_sys_with_mongo.dto;

import com.example.banking_sys_with_mongo.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class TransactionDto {
    String id;
    String account;
    BigDecimal amount;
    TransactionType type;
    String toAccount;
    String fromAccount;
    Date transactionDate;
}
