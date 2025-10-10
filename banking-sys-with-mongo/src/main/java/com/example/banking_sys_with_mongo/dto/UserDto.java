package com.example.banking_sys_with_mongo.dto;

import com.example.banking_sys_with_mongo.model.Account;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {
    String userId;
    String name;
    String email;
    String password;
    Account account;
    Date createdAt;
}
