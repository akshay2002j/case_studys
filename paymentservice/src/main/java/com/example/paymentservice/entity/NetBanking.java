package com.example.paymentservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class NetBanking {
    @Id
    String netBankingUsername;
    String netBankingPassword;
    String bankName;
}
