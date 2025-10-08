package com.example.paymentservice.handler;

import com.example.paymentservice.components.OtpMap;
import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.dto.PaymentRequestDTO;
import com.example.paymentservice.dto.TransactionRequest;
import com.example.paymentservice.interceptor.RequestContext;
import com.example.paymentservice.service.paymentservice.payment_provider.IPaymentProvider;
import com.example.paymentservice.service.paymentservice.paymentprocessor.TransactionService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PaymentHandler {


    private final PaymentTypeBeanMap paymentTypeBeanMap;

    private final TransactionService transactionProcessorService;

    private final RequestContext request;

    private final OtpMap otpMap;

    public PaymentHandler(PaymentTypeBeanMap paymentTypeBeanMap, TransactionService transactionProcessorService, RequestContext request, OtpMap otpMap) {
        this.paymentTypeBeanMap = paymentTypeBeanMap;
        this.transactionProcessorService = transactionProcessorService;
        this.request = request;
        this.otpMap = otpMap;
    }

    public String initiatePayment(PaymentRequestDTO paymentRequestDTO) {
        IPaymentProvider IPaymentProvider = paymentTypeBeanMap.getPaymentType(paymentRequestDTO.getPaymentType());
        if (IPaymentProvider == null) {
            log.debug("Payment Provider not found for Payment Type {}", paymentRequestDTO.getPaymentType());
            return null;
        }
        else {
            if(IPaymentProvider.validateDetails(paymentRequestDTO.getData())){
                String otp = (String) IPaymentProvider.generateOTP();
                String transId =  transactionProcessorService.initiateTransaction(request.getUserID(), paymentRequestDTO.getPaymentType());
                this.otpMap.setOtpMap(transId,otp);
                log.info("Payment Provider Validated OTP is {} and transaction Id is {}", otp,transId);
                return transId;
            }
            else{
                log.debug("Incorrect Payment Provider Data");
                return null;
            }
        }
    }

    public String makePayment(String tranId,String otp, TransactionRequest transactionRequest) {
        if (this.otpMap.containsOtp( tranId)){
            this.otpMap.removeOtp(tranId);
            log.info("OTP={} Validated for User = {} with transaction Id ={}",otp,request.getUserID(),tranId);
            String transId =  transactionProcessorService.makePayment(tranId,transactionRequest);
            return tranId;
        }
        else {
            log.debug("OTP={} validation failed for User = {} with transaction Id ={}",otp,request.getUserID(),tranId);
            return null;
        }
    }

    public String cancelPayment(String tranId) {
        log.info("Cancel Payment for Transaction Id ={}",tranId);
        String id = transactionProcessorService.cancelPayment(tranId);
        if (id != null) {
            log.info("Payment Cancelled for Transaction Id ={}",id);
            return id;
        }
        else {
            log.debug("Payment Cancellation failed for Transaction Id ={}",id,"Failed");
            return null;
        }
    }

}
