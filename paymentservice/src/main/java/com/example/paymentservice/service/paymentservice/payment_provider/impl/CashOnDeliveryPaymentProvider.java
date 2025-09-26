package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.service.paymentservice.payment_provider.AbstractCashOnDeliveryPaymentProvider;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("COD")
public class CashOnDeliveryPaymentProvider extends AbstractCashOnDeliveryPaymentProvider{


    ///  COD - Validate user mobile -> Generate OTP -> Validate OTP

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

    @PostConstruct
    public void init() {
        paymentTypeBeanMap.putPaymentType(PaymentType.COD,this);
    }





}
