package com.example.banking_sys_with_mongo.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String userId;

    @Field("full_name")
    private String name;

    private String email;
    private String password;

    @CreatedDate
    private LocalDateTime createdAt;
}
