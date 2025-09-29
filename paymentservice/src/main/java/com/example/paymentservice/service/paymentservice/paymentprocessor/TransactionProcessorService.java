package com.example.paymentservice.service.paymentservice.paymentprocessor;

import com.example.paymentservice.constant.PaymentStatus;
import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.dto.TransactionRequest;
import com.example.paymentservice.entity.Transaction;
import com.example.paymentservice.entity.User;
import com.example.paymentservice.exception.TransactionNotFound;
import com.example.paymentservice.exception.UserNotFoundException;
import com.example.paymentservice.interceptor.RequestContext;
import com.example.paymentservice.repository.TransactionRepository;
import com.example.paymentservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Slf4j
@Service
public class TransactionProcessorService implements TransactionProcessor {


    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    RequestContext requestContext;

    @Override
    public String initiateTransaction(String userId,PaymentType paymentType) {
        log.info("initiateTransaction for user {}",userId);
        User user  = userRepository.findById(userId).orElseThrow(
                () ->{
                    log.error("User not found for user {}",userId,"For initiateTransaction");
                    return  new UserNotFoundException("User not found with id: " + userId);
                }
        );
        Transaction transaction = new Transaction();
        transaction.setUser(user);
        transaction.setPaymentType(paymentType);
        transaction.setTransactionStatus(PaymentStatus.IN_PROGRESS);
        transaction.setTransactionDate(new Date(System.currentTimeMillis()));
        Transaction savedtransaction =  transactionRepository.save(transaction);
        log.info("Transaction saved successfully for user {}",userId);
        return savedtransaction.getTransactionId();
    }

    @Override
    public String makePayment(String transId, TransactionRequest transactionRequest) {
        Transaction transaction = transactionRepository.findById(transId).orElseThrow(
                () -> {
                    log.error("Transaction not found with id {}", transId,"for user id{}",requestContext.getUserID());
                    return new TransactionNotFound("Transaction not found with id: " + transId);
                }
        );
        transaction.setTransactionAmount(transactionRequest.getAmount());
        transaction.setTransactionStatus(PaymentStatus.SUCCESS);
        Transaction tran = transactionRepository.save(transaction);
        log.info("Transaction saved successfully for user {}",transaction.getUser());
        return tran.getTransactionId();
    }

    @Override
    public String cancelPayment(String transId) {

        Transaction transaction = transactionRepository.findById(transId).orElseThrow(
                () -> {
                    log.error("Transaction not found with id {}", transId,"for user id{}",requestContext.getUserID());
                return new TransactionNotFound("Transaction Not Found with Id:-" + transId);

                }
        );
        transaction.setTransactionStatus(PaymentStatus.CANCELLED);
        transactionRepository.save(transaction);
        log.info("Transaction saved successfully for user {}",transaction.getUser());
        return transId;
    }
}
