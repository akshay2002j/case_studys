package com.example.paymentservice.controller;


import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.service.paymentservice.PaymentService;
import com.example.paymentservice.service.paymentservice.payment_type.PaymentType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final Map<String, PaymentType<?>> paymentTypePaymentServiceMap;

    private final PaymentService paymentService;

    public PaymentController(Map<String, PaymentType<?>> paymentTypePaymentServiceMap ,PaymentService paymentService) {
        this.paymentTypePaymentServiceMap = paymentTypePaymentServiceMap;
        this.paymentService = paymentService;
    }


    @PostMapping("/")
    public ResponseEntity<?> validatePaymentDetail(@RequestBody Payment payment) {
        PaymentType paymentTypeService = paymentTypePaymentServiceMap.get(payment.getPaymentType().name());
        if (paymentTypeService == null) {
            return new ResponseEntity<>("Payment Type Not Supported", HttpStatus.BAD_REQUEST);
        }

        if(paymentTypeService.validateDetails(payment.getCardPayment())){
          String otp = (String)   paymentTypeService.generateOTP();
            return new ResponseEntity<>(  "One Time password for payment is:-  "+ otp, HttpStatus.OK);
        }
        return new ResponseEntity<>("Payment Method Not Found", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/{otp}")
    public ResponseEntity<?> completePaymentWithOTP(@PathVariable String otp, @RequestBody Payment payment) {
        PaymentType paymentTypeService = paymentTypePaymentServiceMap.get(payment.getPaymentType().name());
        if (paymentTypeService == null) {
            return new ResponseEntity<>("Payment Method Not found", HttpStatus.BAD_REQUEST);
        }

        if(paymentTypeService.validateDetails(payment.getCardPayment())){
            paymentService.makePayment(payment);
            return new ResponseEntity<>(  "Payment Successful Payment ID:- "+ payment.getPaymentId(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Payment Method Not Found", HttpStatus.BAD_REQUEST);
    }

}
