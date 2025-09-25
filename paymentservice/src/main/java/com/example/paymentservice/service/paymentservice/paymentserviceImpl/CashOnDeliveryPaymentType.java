package com.example.paymentservice.service.paymentservice.paymentserviceImpl;

import com.example.paymentservice.service.paymentservice.payment_type.AbstractCashOnDeliveryPaymentType;
import org.springframework.stereotype.Service;


@Service("COD")
public class CashOnDeliveryPaymentType extends AbstractCashOnDeliveryPaymentType {


    ///  COD - Validate user mobile -> Generate OTP -> Validate OTP


}
