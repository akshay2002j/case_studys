package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.service.paymentservice.payment_provider.AbstractCardPaymentProvider;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CARD")
public class CardPaymentProvider extends AbstractCardPaymentProvider {

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

    /// Card - Validate Card details, card limit -> Generate OTP -> Validate OTP


    @PostConstruct
    public void init() {
        paymentTypeBeanMap.putPaymentType(PaymentType.CARD,this);
    }

}
