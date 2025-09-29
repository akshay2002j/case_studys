package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.service.paymentservice.payment_provider.AbstractCashOnDeliveryPaymentProvider;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service("COD")
public class CashOnDeliveryPaymentProvider extends AbstractCashOnDeliveryPaymentProvider{


    ///  COD - Validate user mobile -> Generate OTP -> Validate OTP

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

    @PostConstruct
    public void init() {

        paymentTypeBeanMap.putPaymentType(PaymentType.COD,this);
        log.debug("Bean of the COD payment provider injected in HashMap");
    }





}
