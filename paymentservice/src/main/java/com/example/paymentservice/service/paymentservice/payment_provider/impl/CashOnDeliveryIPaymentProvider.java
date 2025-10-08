package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.entity.COD;
import com.example.paymentservice.interceptor.RequestContext;
import com.example.paymentservice.service.paymentservice.payment_provider.AbstractIPaymentProvider;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;


@Slf4j
@Service("COD")
public class CashOnDeliveryIPaymentProvider extends AbstractIPaymentProvider<COD> {


    ///  COD - Validate user mobile -> Generate OTP -> Validate OTP

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

    @Autowired
    RequestContext requestContext;

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


    @PostConstruct
    public void init() {

        paymentTypeBeanMap.putPaymentType(PaymentType.COD,this);
        log.debug("Bean of the COD payment provider injected in HashMap");
    }





}
