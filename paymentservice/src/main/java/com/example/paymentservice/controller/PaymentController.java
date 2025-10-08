package com.example.paymentservice.controller;


import com.example.paymentservice.dto.PaymentRequestDTO;
import com.example.paymentservice.dto.TransactionRequest;
import com.example.paymentservice.handler.PaymentHandler;
import com.example.paymentservice.interceptor.RequestContext;
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


@Slf4j
@Tag(name = "Payment Management", description = "Operations related to payment")
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final RequestContext request;

    private final PaymentHandler paymentHandler;

    public PaymentController(
                             RequestContext request,
                            PaymentHandler paymentHandler
                             ) {
        this.request = request;
        this.paymentHandler = paymentHandler;
    }


    @PostMapping("/")
    @Operation(
            summary = "Initiate the payment request",
            description = "Initiate the payment and get valid otp"
    )
    @ApiResponse(responseCode = "200", description = "Payment initiated  successfully",
            content = @Content(schema = @Schema(implementation =  PaymentRequestDTO.class)))
    @ApiResponse(responseCode = "24",description = "The  payment Type provided by you is not valid")
    public  ResponseEntity<?> initiatePayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        String transId = paymentHandler.initiatePayment(paymentRequestDTO);
        if(transId == null){
            return new ResponseEntity<>("Payment Provider Not Correct",HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(transId, HttpStatus.OK);
        }
    }

    @Operation(summary = "Complete the payment request with valid transaction Id", description = "Validate the transaction Id with Otp and complete the transaction")
    @PostMapping("/{tranId}/{otp}")
    public ResponseEntity<?> makePayment(
            @Parameter(description = "Unique transaction Id")
            @PathVariable String tranId,
            @Parameter(description = "Unique OTP for transaction ID")
            @PathVariable String otp, @RequestBody TransactionRequest transactionRequest){
        String transId = paymentHandler.makePayment(tranId,otp,transactionRequest);
        if(transId == null){
            return new ResponseEntity<>("Transaction Id Not Correct",HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(transId, HttpStatus.OK);
        }
    }

    @Operation(summary = "Cancel the Transaction with  valid transaction Id", description = "Cancel the Payment for given valid transaction Id")
    @DeleteMapping("/{tranId}")
    public ResponseEntity<?> cancelPayment(@Parameter(description = "Unique transaction Id")
            @PathVariable String tranId){
      String id = paymentHandler.cancelPayment(tranId);
      if(id == null){
          return new ResponseEntity<>("Transaction Id Not Correct",HttpStatus.BAD_REQUEST);
      }
     return new ResponseEntity<>("Transaction Cancelled, Transaction Id: "+id,HttpStatus.ACCEPTED);
    }



}
