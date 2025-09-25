package com.example.paymentservice.service.paymentservice.payment_type;

import java.security.SecureRandom;

public abstract class AbstractPaymentType<T> implements PaymentType<T> {

    private static final SecureRandom secureRandom = new SecureRandom();

    @Override
    public String generateOTP() {
        int maxExclusive = (int) Math.pow(10, 4);      // e.g. 10000 for 4 digits
        int otpInt = secureRandom.nextInt(maxExclusive);    // 0 .. maxExclusive-1
        return String.format("%0" + 4 + "d", otpInt);
    }

    @Override
    public <O> boolean validateOTP(O otp) {
        if (otp instanceof String otpString) {
            return otpString.length() == 4;
        }
        return false;
    }
}
