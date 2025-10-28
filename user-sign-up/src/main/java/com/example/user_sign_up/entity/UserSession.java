package com.example.user_sign_up.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;


@Data
@Entity
public class UserSession {
    @Id
            @GeneratedValue(strategy = GenerationType.UUID)
    String sessionId;
    String email;
    LocalDateTime createdAt;
    LocalDateTime expiresAt;
}

