package com.example.banking_sys_with_mongo.dto.requestdto;

import com.example.banking_sys_with_mongo.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DepositRequest {
    String accountNumber;
    BigDecimal amount;
    TransactionType type;
}
