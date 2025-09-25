package com.example.paymentservice.service.paymentservice;

import com.example.paymentservice.constant.PaymentStatus;
import com.example.paymentservice.entity.Payment;
import com.example.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {


    @Autowired
    PaymentRepository paymentRepository;

    public Payment makePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment getPaymentById( String paymentId) {
        return paymentRepository.getReferenceById(paymentId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public void  deletePaymentById( String paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    public PaymentStatus changePaymentStatus( String paymentId, PaymentStatus paymentStatus) {
        Payment payment = paymentRepository.getReferenceById(paymentId);
        payment.setPaymentStatus(paymentStatus);
        paymentRepository.save(payment);
        return paymentStatus;
    }

}
