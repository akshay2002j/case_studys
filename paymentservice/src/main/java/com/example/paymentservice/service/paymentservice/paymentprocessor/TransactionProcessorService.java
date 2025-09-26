package com.example.paymentservice.service.paymentservice.paymentprocessor;

import com.example.paymentservice.constant.PaymentStatus;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.dto.TransactionRequest;
import com.example.paymentservice.entity.Transaction;
import com.example.paymentservice.entity.User;
import com.example.paymentservice.repository.TransactionRepository;
import com.example.paymentservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class TransactionProcessorService implements TransactionProcessor {


    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public String initiateTransaction(String userId,PaymentType paymentType) {
        User user  = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found!")
        );
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setPaymentType(paymentType);
        transaction.setTransactionStatus(PaymentStatus.IN_PROGRESS);
        transaction.setTransactionDate(new Date(System.currentTimeMillis()));
        Transaction savedtransaction =  transactionRepository.save(transaction);
        return savedtransaction.getTransactionId();
    }

    @Override
    public String makePayment(String transId, TransactionRequest transactionRequest) {
        Transaction transaction = transactionRepository.findById(transId).orElseThrow(
                () -> new RuntimeException("Transaction not found!")
        );
        transaction.setTransactionAmount(transactionRequest.getAmount());
        transaction.setTransactionStatus(PaymentStatus.SUCCESS);
        Transaction tran = transactionRepository.save(transaction);
        return tran.getTransactionId();
    }

    @Override
    public String cancelPayment(String transId) {

        Transaction transaction = transactionRepository.findById(transId).orElseThrow(
                () -> new RuntimeException("Transaction not found!")
        );
        transaction.setTransactionStatus(PaymentStatus.CANCELLED);
        transactionRepository.save(transaction);
        return transId;
    }
}
