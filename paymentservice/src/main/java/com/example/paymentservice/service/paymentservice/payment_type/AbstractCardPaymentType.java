package com.example.paymentservice.service.paymentservice.payment_type;

import com.example.paymentservice.Helper.Helper;
import com.example.paymentservice.entity.CardPayment;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class AbstractCardPaymentType extends AbstractPaymentType<CardPayment> {


    @Override
    public boolean validateDetails(CardPayment card){
        if(card!= null){
            log.info("CardPayment validated");
            return Helper.validateCardDetails(card.getCvv(),card.getCardNumber());
        }
        else {
            log.error("CardPayment object is null");
            return false;
        }
    }
}
