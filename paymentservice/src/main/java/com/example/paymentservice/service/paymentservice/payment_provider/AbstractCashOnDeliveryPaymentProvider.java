package com.example.paymentservice.service.paymentservice.payment_provider;

import com.example.paymentservice.entity.COD;
import com.example.paymentservice.interceptor.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Slf4j
public abstract class AbstractCashOnDeliveryPaymentProvider extends AbstractPaymentProvider<COD> {

    @Autowired
    RequestContext  requestContext;

    @Override
    public boolean validateDetails(Map<String, Object> data) {
        if (data!=null){
            log.debug("Received request to validate user {}",requestContext.getUserID());
            return data.get("mobile").toString().length()==10 && data.get("pincode").toString().length()==6;
        }
        else {
            log.error("Invalid data provided data={}",data);
            return false;
        }
    }

}
