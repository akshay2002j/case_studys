package com.example.paymentservice.controller;


import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.dto.InitiatePaymentResponse;
import com.example.paymentservice.dto.PaymentRequestDTO;

import com.example.paymentservice.dto.TransactionRequest;
import com.example.paymentservice.interceptor.RequestContext;
import com.example.paymentservice.service.paymentservice.paymentprocessor.TransactionProcessorService;
import com.example.paymentservice.service.paymentservice.payment_provider.PaymentProvider;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

   private final PaymentTypeBeanMap paymentTypeBeanMap;

    private final TransactionProcessorService transactionProcessorService;

    private final RequestContext request;

    Map<String,String> topMap = new HashMap<>();

    public PaymentController(PaymentTypeBeanMap paymentTypeBeanMap, TransactionProcessorService transactionProcessorService, RequestContext request) {
      this.paymentTypeBeanMap = paymentTypeBeanMap;
        this.transactionProcessorService = transactionProcessorService;
        this.request = request;
    }


    @PostMapping("/")
    public  ResponseEntity<?> initiatePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentProvider paymentProvider = paymentTypeBeanMap.getPaymentType(paymentRequestDTO.getPaymentType());
       // System.out.println(paymentRequestDTO.getData());
        if (paymentProvider == null) {
            return new ResponseEntity<>("Payment Type Not Supported",HttpStatus.BAD_REQUEST);
        }
        else {
           if(paymentProvider.validateDetails(paymentRequestDTO.getData())){
               String top = (String) paymentProvider.generateOTP();
             String transId =  transactionProcessorService.initiateTransaction(request.getUserID(), paymentRequestDTO.getPaymentType());
               this.topMap.put(transId,top);
             return new ResponseEntity<>(new InitiatePaymentResponse(top,transId),HttpStatus.OK);
           }
           else{
               return new ResponseEntity<>("Details Not Correct",HttpStatus.BAD_REQUEST);
            }
        }
    }

    @PostMapping("/{tranId}/{otp}")
    public ResponseEntity<?> makePayment(@PathVariable String tranId, @PathVariable String otp, @RequestBody TransactionRequest transactionRequest){

        if (this.topMap.containsKey(tranId)){
            this.topMap.remove(tranId);
         String transId =  transactionProcessorService.makePayment(tranId,transactionRequest);
         return new ResponseEntity<>("Transaction Successfully, Transaction Id: "+transId,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Details Not Correct",HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{tranId}")
    public ResponseEntity<?> cancelPayment(@PathVariable String tranId){
     String id = transactionProcessorService.cancelPayment(tranId);
     return new ResponseEntity<>("Transaction Cancelled, Transaction Id: "+id,HttpStatus.ACCEPTED);

    }



}
