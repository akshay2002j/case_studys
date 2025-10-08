package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.entity.UPI;
import com.example.paymentservice.service.paymentservice.payment_provider.AbstractIPaymentProvider;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service("UPI")
public class UPIIPaymentProvider extends AbstractIPaymentProvider<UPI> {

    /// 3. UPI - Validate UPI ID -> Validate PIN

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

    @Override
    public boolean validateDetails(Map<String, Object> data) {
        log.debug("Received data {}",data.get("upiId"),"for validation In UPIPaymentProvider");
        return data.get("upiId") != null && data.get("upiId").toString().length() == 14;
    }

    @PostConstruct
    public void init() {
        paymentTypeBeanMap.putPaymentType(PaymentType.UPI,this);
        log.debug("Bean of the UPI payment provider injected in HashMap");
    }

}
