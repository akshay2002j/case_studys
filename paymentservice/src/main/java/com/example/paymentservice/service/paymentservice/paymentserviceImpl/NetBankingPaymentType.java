package com.example.paymentservice.service.paymentservice.paymentserviceImpl;

import com.example.paymentservice.service.paymentservice.payment_type.AbstractNetBankingPaymentType;
import org.springframework.stereotype.Service;

@Service("NETBANKING")
public class NetBankingPaymentType extends AbstractNetBankingPaymentType {

    /// Netbanking - Validate userName/password, balance -> Generate OTP -> Validate OTP


}
