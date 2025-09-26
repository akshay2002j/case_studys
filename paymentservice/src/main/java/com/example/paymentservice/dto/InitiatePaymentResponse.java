package com.example.paymentservice.dto;

import lombok.Data;

@Data
public class InitiatePaymentResponse {
    String top;
    String transactionId;

    public InitiatePaymentResponse(String top, String transactionId) {
        this.top = top;
        this.transactionId = transactionId;
    }
}
