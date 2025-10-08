package com.example.paymentservice.components;


import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.service.paymentservice.payment_provider.IPaymentProvider;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentTypeBeanMap {

    private final Map<PaymentType, IPaymentProvider>paymentTypeMap;

    public PaymentTypeBeanMap() {
       this.paymentTypeMap = new HashMap<>();
    }

    public void putPaymentType(PaymentType paymentType, IPaymentProvider IPaymentProvider) {
        paymentTypeMap.put(paymentType, IPaymentProvider);
    }

    public IPaymentProvider getPaymentType(PaymentType paymentType) {
        return paymentTypeMap.get(paymentType);
    }
}
