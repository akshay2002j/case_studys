package com.example.paymentservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Entity
@Data
public class User {
    @Id
            @GeneratedValue(strategy = GenerationType.UUID)
    String userId;
    String userEmail;
    String userPassword;
    @OneToMany
    List<Payment> paymentList = new ArrayList<>();
}
