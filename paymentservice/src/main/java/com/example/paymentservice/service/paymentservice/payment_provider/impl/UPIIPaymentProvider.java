package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.service.paymentservice.payment_provider.AbstractUPIPaymentProvider;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service("UPI")
public class UPIPaymentProvider extends AbstractUPIPaymentProvider {

    /// 3. UPI - Validate UPI ID -> Validate PIN

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

    @PostConstruct
    public void init() {
        paymentTypeBeanMap.putPaymentType(PaymentType.UPI,this);
        log.debug("Bean of the UPI payment provider injected in HashMap");
    }

}
