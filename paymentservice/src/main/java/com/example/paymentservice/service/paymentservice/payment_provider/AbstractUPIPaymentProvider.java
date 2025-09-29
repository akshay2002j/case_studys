package com.example.paymentservice.service.paymentservice.payment_provider;

import com.example.paymentservice.entity.UPI;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public abstract class AbstractUPIPaymentProvider extends AbstractPaymentProvider<UPI> {

    @Override
    public boolean validateDetails(Map<String, Object> data) {
       log.debug("Received data {}",data.get("upiId"),"for validation In UPIPaymentProvider");
        return data.get("upiId") != null && data.get("upiId").toString().length() == 14;
    }
}
