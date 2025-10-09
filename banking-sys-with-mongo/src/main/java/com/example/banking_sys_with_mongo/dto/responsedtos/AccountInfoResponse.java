package com.example.banking_sys_with_mongo.dto.responsedtos;

import com.example.banking_sys_with_mongo.model.User;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountInfoResponse {

    String accountId;
    String accountNumber;
    User user;
    BigDecimal balance;
}
