package com.example.paymentservice.entity;

import com.example.paymentservice.constant.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.lang.NonNull;

import java.sql.Date;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String paymentId;
    String paymentType;
    PaymentStatus paymentStatus;
    Date paymentDate;
    Double paymentAmount;
}
