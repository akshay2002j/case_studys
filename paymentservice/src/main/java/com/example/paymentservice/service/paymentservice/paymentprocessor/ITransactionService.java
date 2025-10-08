package com.example.paymentservice.service.paymentservice.paymentprocessor;

import com.example.paymentservice.constant.PaymentType;
import com.example.paymentservice.dto.TransactionRequest;

public interface ITransactionService {
   String initiateTransaction(String userId,PaymentType paymentType);
   String makePayment(String transId, TransactionRequest transactionRequest);
   String cancelPayment(String transId);
}
