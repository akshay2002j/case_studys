package com.example.paymentservice.service.paymentservice.payment_type;

public interface PaymentType<T> {
   boolean validateDetails(T entity);
   <N> N generateOTP();
   <O> boolean validateOTP(O otp);
}


