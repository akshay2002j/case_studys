package com.example.paymentservice.service.paymentservice.payment_provider;
import com.example.paymentservice.Helper.Helper;
import com.example.paymentservice.entity.Card;
import com.example.paymentservice.interceptor.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


@Slf4j
public abstract class AbstractCardPaymentProvider extends AbstractPaymentProvider<Card> {

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

}
