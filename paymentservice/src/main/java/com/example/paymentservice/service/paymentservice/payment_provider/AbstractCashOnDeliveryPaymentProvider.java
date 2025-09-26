package com.example.paymentservice.service.paymentservice.payment_provider;

import com.example.paymentservice.entity.COD;

import java.util.Map;

public abstract class AbstractCashOnDeliveryPaymentProvider extends AbstractPaymentProvider<COD> {

    @Override
    public boolean validateDetails(Map<String, Object> data) {
        if (data!=null){
            return data.get("mobile").toString().length()==10 && data.get("pincode").toString().length()==6;
        }
        else {
            return false;
        }
    }

}
