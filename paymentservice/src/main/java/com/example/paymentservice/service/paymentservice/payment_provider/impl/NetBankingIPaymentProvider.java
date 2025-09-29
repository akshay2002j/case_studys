package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.service.paymentservice.payment_provider.AbstractNetBankingPaymentProvider;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("NETBANKING")
public class NetBankingPaymentProvider extends AbstractNetBankingPaymentProvider {

    /// Netbanking - Validate userName/password, balance -> Generate OTP -> Validate OTP

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

    @PostConstruct
    public void init() {
        paymentTypeBeanMap.putPaymentType(PaymentType.NETBANKING,this);
        log.debug("Bean of the NetBanking payment provider injected in HashMap");
    }

}
