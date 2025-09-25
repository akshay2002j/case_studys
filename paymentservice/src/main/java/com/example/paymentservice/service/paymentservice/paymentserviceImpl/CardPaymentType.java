package com.example.paymentservice.service.paymentservice.paymentserviceImpl;

import com.example.paymentservice.service.paymentservice.payment_type.AbstractCardPaymentType;

import org.springframework.stereotype.Service;

@Service("CARD")
public class CardPaymentType extends AbstractCardPaymentType {

    /// Card - Validate Card details, card limit -> Generate OTP -> Validate OTP


}
