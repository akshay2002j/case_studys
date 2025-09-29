package com.example.paymentservice.service.paymentservice.payment_provider;

import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;

@Slf4j
public abstract class AbstractPaymentProvider<T> implements PaymentProvider<T> {

    private static final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generateOTP() {
        log.info("Generating OTP");
        int maxExclusive = (int) Math.pow(10, 4);      // e.g. 10000 for 4 digits
        int otpInt = secureRandom.nextInt(maxExclusive);// 0 .. maxExclusive-1
        log.debug("Generated OTP {}",otpInt);
        return String.format("%0" + 4 + "d", otpInt);
    }

    @Override
    public <O> boolean validateOTP(O otp) {
        log.info("Validating OTP");
        if (otp instanceof String otpString) {
            log.debug("Received OTP {}",otpString);
            return otpString.length() == 4;
        }
        log.debug("Received OTP {}",otp.toString());
        return false;
    }
}
