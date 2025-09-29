package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.service.paymentservice.payment_provider.AbstractCardPaymentProvider;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("CARD")
public class CardPaymentProvider extends AbstractCardPaymentProvider {

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

    /// Card - Validate Card details, card limit -> Generate OTP -> Validate OTP


    @PostConstruct
    public void init() {
        paymentTypeBeanMap.putPaymentType(PaymentType.CARD,this);
        log.debug("Bean of the CARD payment provider injected in HashMap");
    }

}
