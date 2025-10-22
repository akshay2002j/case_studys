package com.example.user_sign_up.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String Id;
    private String name;
    private String email;
    private String password; // In a real application, passwords should be hashed, not stored as plain text.
    private String phoneNumber;
}
