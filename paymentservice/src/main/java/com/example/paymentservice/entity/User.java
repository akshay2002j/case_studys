package com.example.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;


@Entity
@Data
public class User {
    @Id
            @GeneratedValue(strategy = GenerationType.UUID)
    String userId;
    String userEmail;
    String userPassword;
    List<Payment> paymentList;
}
