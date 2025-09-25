package com.example.paymentservice.Helper;

import java.security.SecureRandom;



public class Helper {

    private static final String CVV_3_DIGIT = "^[0-9]{3}$";
    private static final String CARD_REGEX = "^4[0-9]{12}$";

    private static final SecureRandom secureRandom = new SecureRandom();

    public static boolean validateCardDetails(String cvv, String cardNumber){
       return cvv.matches(CVV_3_DIGIT) && cardNumber.matches(CARD_REGEX);
    }

    public  String generateNumericOtp(int digits) {
        if (digits <= 0) throw new IllegalArgumentException("digits must be positive");
        int maxExclusive = (int) Math.pow(10, digits);      // e.g. 10000 for 4 digits
        int otpInt = secureRandom.nextInt(maxExclusive);    // 0 .. maxExclusive-1
        return String.format("%0" + digits + "d", otpInt);  // preserve leading zeros
    }

}
