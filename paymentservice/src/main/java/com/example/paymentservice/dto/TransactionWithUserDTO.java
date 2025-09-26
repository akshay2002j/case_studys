package com.example.paymentservice.dto;

import java.util.Date;

public class TransactionWithUserDTO {
    private Long transactionId;
    private String paymentType;
    private Double transactionAmount;
    private Date transactionDate;
    private String transactionStatus;
    private Long userId;
    private String userEmail;
    private String userPassword;

    public TransactionWithUserDTO(Long transactionId, String paymentType, Double transactionAmount,
                                  Date transactionDate, String transactionStatus,
                                  Long userId, String userEmail, String userPassword) {
        this.transactionId = transactionId;
        this.paymentType = paymentType;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.transactionStatus = transactionStatus;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }
}
