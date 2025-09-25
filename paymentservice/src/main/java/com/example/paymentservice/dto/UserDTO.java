package com.example.paymentservice.dto;

import com.example.paymentservice.entity.Payment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserDTO {
    String userId;
    String userEmail;
    String userPassword;
    List<Payment> paymentList;
}
