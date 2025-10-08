package com.example.banking_sys_with_mongo.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AccountDto {

    String accountId;
    String accountNumber;
    String userId;
    BigDecimal balance;
}
