package com.example.paymentservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Embeddable
public class CardPayment {
    String cardNumber;
    String expirationDate;
    String cvv;
    String cardHolderName;
    @Column(name = "credit_limit")
    Integer limit;
}
