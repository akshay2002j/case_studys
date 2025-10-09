package com.example.banking_sys_with_mongo.model;
import lombok.Data;



import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Account {

    private String accountId;
    private String accountNumber;
    private User user;
    private BigDecimal balance;
    private LocalDateTime updatedAt;
    private LocalDateTime createdAt;
}

