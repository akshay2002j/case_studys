package com.example.banking_sys_with_mongo.dto.requestdto;

import com.example.banking_sys_with_mongo.model.TransactionType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    String account;
    BigDecimal amount;
    TransactionType type;
    String toAccountNumber;
    String fromAccountNumber;
}
