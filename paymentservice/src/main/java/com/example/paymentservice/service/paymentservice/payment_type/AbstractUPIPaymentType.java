package com.example.paymentservice.service.paymentservice.payment_type;

import com.example.paymentservice.entity.Payment;

public abstract class AbstractUPIPaymentType extends AbstractPaymentType<Payment> {

    @Override
    public boolean validateDetails(Payment entity) {
        return entity.getUpiId() != null && entity.getUpiId().length() == 14;
    }
}
