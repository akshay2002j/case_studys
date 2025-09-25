package com.example.paymentservice.service.paymentservice.payment_type;

import com.example.paymentservice.entity.Payment;

public abstract class AbstractCashOnDeliveryPaymentType extends  AbstractPaymentType<Payment> {

    @Override
    public boolean validateDetails(Payment cashOnDelivery) {
        if (cashOnDelivery!=null){
            return  cashOnDelivery.getMobileNo().length()==10;
        }
        else {
            return false;
        }
    }

}
