package com.example.paymentservice.components;


import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.service.paymentservice.payment_provider.PaymentProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentTypeBeanMap {

    private final Map<PaymentType, PaymentProvider >paymentTypeMap;

    public PaymentTypeBeanMap() {
       this.paymentTypeMap = new HashMap<>();
    }

    public void putPaymentType(PaymentType paymentType, PaymentProvider paymentProvider) {
        paymentTypeMap.put(paymentType, paymentProvider);
    }

    public PaymentProvider getPaymentType(PaymentType paymentType) {
        return paymentTypeMap.get(paymentType);
    }
}
