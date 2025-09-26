package com.example.paymentservice.entity;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class COD {
    String mobile;
    String pincode;
}
