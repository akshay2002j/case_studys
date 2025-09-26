package com.example.paymentservice.service.paymentservice.payment_provider;
import com.example.paymentservice.Helper.Helper;
import com.example.paymentservice.entity.Card;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;


@Slf4j
public abstract class AbstractCardPaymentProvider extends AbstractPaymentProvider<Card> {


    @Override
    public boolean validateDetails(Map<String,Object> data ) {
        System.out.println(data);
        if(data!= null){
            log.info("CardPayment validated");
           return Helper.validateCardDetails(data.get("cvv").toString(), data.get("cardNumber").toString());
        }
        else {
            log.error("CardPayment object is null");
            return false;
        }
    }

}
