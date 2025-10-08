package com.example.banking_sys_with_mongo.service;

import com.example.banking_sys_with_mongo.dto.TransactionDto;
import com.example.banking_sys_with_mongo.model.Account;
import com.example.banking_sys_with_mongo.model.Transaction;
import com.example.banking_sys_with_mongo.repository.AccountRepository;
import com.example.banking_sys_with_mongo.repository.TransactionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {

    TransactionRepository transactionRepository;

    AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository , AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public TransactionDto createTransaction(TransactionDto transactionDto) {
       Account account = accountRepository.findAccountByAccountNumber(transactionDto.getAccountNumber()).orElseThrow(
               () -> new RuntimeException("Account number not found")
       );
       Transaction transaction = new Transaction();
       BeanUtils.copyProperties(transactionDto,transaction);
       transaction.setAccount(account);
       Transaction savedTran =  transactionRepository.save(transaction);
       transactionDto.setTransactionDate(savedTran.getTransactionDate());
       transactionDto.setId(savedTran.getId());
       return transactionDto;
    }

    public TransactionDto updateTransaction(TransactionDto transactionDto) {
        Account account = accountRepository.findAccountByAccountNumber(transactionDto.getAccountNumber()).orElseThrow(
                () -> new RuntimeException("Account number not found")
        );
       Transaction savedTransaction = transactionRepository.findById(transactionDto.getId()).orElseThrow(
                () -> new RuntimeException("Transaction id not found")
        );
       savedTransaction.setAmount(transactionDto.getAmount());
       transactionRepository.save(savedTransaction);
       transactionDto.setTransactionDate(savedTransaction.getTransactionDate());
       transactionDto.setType(savedTransaction.getType());
       return transactionDto;
    }

    public  String deleteTransaction(String id) {
        transactionRepository.deleteById(id);
        return "Transaction id "+id+" deleted";
    }

    public TransactionDto getTransactionById(String id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Transaction id not found")
        );
        TransactionDto transactionDto = new TransactionDto();
        BeanUtils.copyProperties(transaction,transactionDto);
        return transactionDto;
    }

}
