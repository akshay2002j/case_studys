package com.example.paymentservice.components;


import java.util.HashMap;
import java.util.Map;

public class OtpMap {
    Map<String,String> otpMap;

    public OtpMap() {
        this.otpMap = new HashMap<>();
    }

    public String getOtpMap(String transactionId) {
        return otpMap.get(transactionId);
    }
    public void setOtpMap(String tranId , String Otp) {
        otpMap.put(tranId , Otp);
    }
    public boolean containsOtp(String transactionId) {
        return otpMap.containsKey(transactionId);
    }
    public boolean removeOtp(String transactionId) {
        return otpMap.remove(transactionId) != null;
    }
}
