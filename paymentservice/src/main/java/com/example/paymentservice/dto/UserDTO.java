package com.example.paymentservice.dto;

import com.example.paymentservice.entity.Transaction;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    String userId;
    String userEmail;
    String userPassword;
    List<Transaction> transactionList;
}
