package com.example.paymentservice.service.paymentservice.payment_provider;

import java.util.Map;

public interface IPaymentProvider<T>{
   boolean validateDetails(Map<String,Object> data );
   <N> N generateOTP();
   <O> boolean validateOTP(O otp);
}


