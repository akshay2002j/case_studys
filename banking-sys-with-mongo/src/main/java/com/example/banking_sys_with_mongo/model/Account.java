package com.example.banking_sys_with_mongo.model;
import lombok.Data;



import java.math.BigDecimal;

import java.util.Date;

@Data
public class Account {

    private String accountId;
    private String accountNumber;
    private String user;
    private BigDecimal balance;
    private Date updatedAt;
    private Date createdAt;
}

