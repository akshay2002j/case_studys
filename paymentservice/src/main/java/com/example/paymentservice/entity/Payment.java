package com.example.paymentservice.entity;

import com.example.paymentservice.constant.PaymentStatus;
import com.example.paymentservice.constant.PaymentType;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.lang.NonNull;

import java.sql.Date;

@Data
@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String paymentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Column(name = "payment_date")
    private Date paymentDate;

    private Double paymentAmount;

    // Optional, depending on type
    @Embedded
    private CardPayment cardPayment;

    private String upiId;

    private String netBankingUsername;
    private String netBankingPassword;

    private String mobileNo;
}

