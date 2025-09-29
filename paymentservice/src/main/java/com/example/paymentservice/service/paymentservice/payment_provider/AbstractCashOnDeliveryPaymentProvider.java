package com.example.paymentservice.service.paymentservice.payment_provider;

import com.example.paymentservice.entity.COD;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public abstract class AbstractCashOnDeliveryPaymentProvider extends AbstractPaymentProvider<COD> {

    @Override
    public boolean validateDetails(Map<String, Object> data) {
        if (data!=null){
            log.debug("Received request to validate user {}",data);
            return data.get("mobile").toString().length()==10 && data.get("pincode").toString().length()==6;
        }
        else {
            log.error("Invalid data provided data={}",data);
            return false;
        }
    }

}
