package com.example.banking_sys_with_mongo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;

    @DBRef
    private Account account;

    private BigDecimal amount;
    private TransactionType type;

    private String toAccount;
    private String fromAccount;

    private LocalDateTime transactionDate = LocalDateTime.now();
}

