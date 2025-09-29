package com.example.paymentservice.controller;


import com.example.paymentservice.components.PaymentTypeBeanMap;
import com.example.paymentservice.dto.InitiatePaymentResponse;
import com.example.paymentservice.dto.PaymentRequestDTO;

import com.example.paymentservice.dto.TransactionRequest;
import com.example.paymentservice.interceptor.RequestContext;
import com.example.paymentservice.service.paymentservice.paymentprocessor.TransactionProcessorService;
import com.example.paymentservice.service.paymentservice.payment_provider.PaymentProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Tag(name = "Payment Management", description = "Operations related to payment")
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
    @Operation(summary = "Initiate the payment request", description = "Initiate the payment and get valid otp")
    @ApiResponse(responseCode = "200", description = "Payment initiated  successfully",
            content = @Content(schema = @Schema(implementation =  PaymentRequestDTO.class)))
    @ApiResponse(responseCode = "24",description = "The  payment Type provided by you is not valid")
    public  ResponseEntity<?> initiatePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        PaymentProvider paymentProvider = paymentTypeBeanMap.getPaymentType(paymentRequestDTO.getPaymentType());
        if (paymentProvider == null) {
            log.debug("Payment Provider not found for Payment Type {}", paymentRequestDTO.getPaymentType());
            return new ResponseEntity<>("Payment Type Not Supported",HttpStatus.BAD_REQUEST);
        }
        else {
           if(paymentProvider.validateDetails(paymentRequestDTO.getData())){
               String top = (String) paymentProvider.generateOTP();
               String transId =  transactionProcessorService.initiateTransaction(request.getUserID(), paymentRequestDTO.getPaymentType());
               this.topMap.put(transId,top);
               log.info("Payment Provider Validated OTP is {} and transaction Id is {}", top,transId);
             return new ResponseEntity<>(new InitiatePaymentResponse(top,transId),HttpStatus.OK);
           }
           else{
               log.debug("Incorrect Payment Provider Data");
               return new ResponseEntity<>("Details Not Correct",HttpStatus.BAD_REQUEST);
            }
        }
    }

    @Operation(summary = "Complete the payment request with valid transaction Id", description = "Validate the transaction Id with Otp and complete the transaction")
    @PostMapping("/{tranId}/{otp}")
    public ResponseEntity<?> makePayment(
            @Parameter(description = "Unique transaction Id")
            @PathVariable String tranId,
            @Parameter(description = "Unique OTP for transaction ID")
            @PathVariable String otp, @RequestBody TransactionRequest transactionRequest){
        if (this.topMap.containsKey(tranId)){
            this.topMap.remove(tranId);
            log.info("OTP={} Validated for User = {} with transaction Id ={}",otp,request.getUserID(),tranId);
         String transId =  transactionProcessorService.makePayment(tranId,transactionRequest);
         return new ResponseEntity<>("Transaction Successfully, Transaction Id: "+transId,HttpStatus.OK);
        }
        else {
            log.debug("OTP={} validation failed for User = {} with transaction Id ={}",otp,request.getUserID(),tranId);
            return new ResponseEntity<>("OTP Not Correct",HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Cancel the Transaction with  valid transaction Id", description = "Cancel the Payment for given valid transaction Id")
    @DeleteMapping("/{tranId}")
    public ResponseEntity<?> cancelPayment(@Parameter(description = "Unique transaction Id")
            @PathVariable String tranId){
        log.info("Cancel Payment for Transaction Id ={}",tranId);
        String id = transactionProcessorService.cancelPayment(tranId);
     return new ResponseEntity<>("Transaction Cancelled, Transaction Id: "+id,HttpStatus.ACCEPTED);

    }



}
