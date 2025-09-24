package com.example.paymentservice.service.paymentservice.paymentserviceImpl;

import com.example.paymentservice.service.paymentservice.PaymentService;
import org.springframework.stereotype.Service;

@Service
public class CardPaymentService implements PaymentService {

    @Override
    public void initiatePayment(Object o) {

    }

    @Override
    public Object completePayment() {
        return null;
    }

    @Override
    public void cancelPayment(Object o) {

    }
}
