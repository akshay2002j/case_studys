package com.example.banking_sys_with_mongo.dto.responsedtos;

import com.example.banking_sys_with_mongo.model.Account;
import com.example.banking_sys_with_mongo.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TransactionInfoResponse {
    String id;
    String account;
    BigDecimal amount;
    TransactionType type;
    LocalDateTime transactionDate;
}
