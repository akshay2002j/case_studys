package com.example.banking_sys_with_mongo.model;

import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Data

public class Transaction {

    private String id;
    private String account;
    private BigDecimal amount;
    private TransactionType type;
    private String toAccount;
    private String fromAccount;
    private Date transactionDate;
}

