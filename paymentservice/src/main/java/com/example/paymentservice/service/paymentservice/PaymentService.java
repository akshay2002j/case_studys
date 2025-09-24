package com.example.paymentservice.service.paymentservice;

public interface PaymentService<T> {
    void initiatePayment(T t);
    T completePayment();
    <L> void cancelPayment( L l);
}
