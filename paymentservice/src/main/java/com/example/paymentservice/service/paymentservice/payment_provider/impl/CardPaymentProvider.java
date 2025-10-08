package com.example.paymentservice.service.paymentservice.payment_provider.impl;

import com.example.paymentservice.Helper.Helper;
import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.entity.Card;
import com.example.paymentservice.interceptor.RequestContext;

import com.example.paymentservice.service.paymentservice.payment_provider.AbstractIPaymentProvider;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service("CARD")
public class CardPaymentProvider extends AbstractIPaymentProvider<Card>   {

    @Autowired
    private PaymentTypeBeanMap paymentTypeBeanMap;

    /// Card - Validate Card details, card limit -> Generate OTP -> Validate OTP
    @Autowired
    RequestContext requestContext;

    @Override
    public boolean validateDetails(Map<String,Object> data ) {
        log.debug("Validating user {}",data.get("cardHolderName"),"for Card PaymentProvider");
        if(data!= null){
            log.info("CardPayment validated for the User {}",requestContext.getUserID());
            return Helper.validateCardDetails(data.get("cvv").toString(), data.get("cardNumber").toString());
        }
        else {
            log.error("CardPayment object is null for the User {}",requestContext.getUserID());
            return false;
        }
    }


    @PostConstruct
    public void init() {
        paymentTypeBeanMap.putPaymentType(PaymentType.CARD,this);
        log.debug("Bean of the CARD payment provider injected in HashMap");
    }

}
