package com.example.banking_sys_with_mongo.dto.requestdto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OpenAccountRequest {
    String userId;
    BigDecimal balance;
}
