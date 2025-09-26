package com.example.paymentservice.dto;

import com.example.paymentservice.constant.PaymentType;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class PaymentRequestDTO {
    private PaymentType paymentType;
    private Map<String, Object> data;
}
