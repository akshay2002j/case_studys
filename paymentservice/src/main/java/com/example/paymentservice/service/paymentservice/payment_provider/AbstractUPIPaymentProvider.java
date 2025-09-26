package com.example.paymentservice.service.paymentservice.payment_provider;

import com.example.paymentservice.entity.UPI;

import java.util.Map;

public abstract class AbstractUPIPaymentProvider extends AbstractPaymentProvider<UPI> {

    @Override
    public boolean validateDetails(Map<String, Object> data) {
        System.out.println(data.toString());
        return data.get("upiId") != null && data.get("upiId").toString().length() == 14;
    }
}
