package com.example.banking_sys_with_mongo.model;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Document(collection = "accounts")
public class Account {

    @Id
    private String accountId;

    @Field("account_number")
    private String accountNumber;

    @DBRef
    private User user;

    @Field("balance")
    private BigDecimal balance;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @CreatedDate
    private LocalDateTime createdAt;



}

