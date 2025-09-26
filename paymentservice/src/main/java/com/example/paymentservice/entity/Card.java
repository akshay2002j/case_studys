package com.example.paymentservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Card {
    @Id
    String cardNumber;
    String expirationDate;
    String cvv;
    String cardHolderName;
    @Column(name = "credit_limit")
    Integer limit;
}
