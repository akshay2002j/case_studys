package com.example.banking_sys_with_mongo.model;

import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data

public class Transaction {

    private String id;

    private Account account;

    private BigDecimal amount;
    private TransactionType type;

    private String toAccount;
    private String fromAccount;

    private LocalDateTime transactionDate = LocalDateTime.now();
}

