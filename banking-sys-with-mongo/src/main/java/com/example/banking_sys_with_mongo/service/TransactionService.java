//package com.example.banking_sys_with_mongo.service;
//
//import com.example.banking_sys_with_mongo.dto.TransactionDto;
//import com.example.banking_sys_with_mongo.dto.requestdto.DepositRequest;
//import com.example.banking_sys_with_mongo.dto.requestdto.TransferRequest;
//import com.example.banking_sys_with_mongo.dto.requestdto.WithdrawRequest;
//import com.example.banking_sys_with_mongo.dto.responsedtos.TransactionInfoResponse;
//import com.example.banking_sys_with_mongo.exception.AccountException;
//import com.example.banking_sys_with_mongo.model.Account;
//import com.example.banking_sys_with_mongo.model.Transaction;
//import com.example.banking_sys_with_mongo.repository.AccountRepository;
//import com.example.banking_sys_with_mongo.repository.TransactionRepository;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class TransactionService {
//
//    TransactionRepository transactionRepository;
//
//    AccountRepository accountRepository;
//
//    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository) {
//        this.transactionRepository = transactionRepository;
//        this.accountRepository = accountRepository;
//    }
//
//    public TransactionDto depositTransaction(DepositRequest depositRequest) {
//        Account account = accountRepository.findAccountByAccountNumber(depositRequest.getAccountNumber()).
//                orElseThrow(() -> new RuntimeException("Account number not found"));
//        Transaction transaction = new Transaction();
//        BeanUtils.copyProperties(depositRequest, transaction);
//        transaction.setAccount(account);
//        account.setBalance(account.getBalance().add(depositRequest.getAmount()));
//        accountRepository.save(account);
//        Transaction savedTran = transactionRepository.save(transaction);
//        TransactionDto transactionDto = new TransactionDto();
//        BeanUtils.copyProperties(savedTran, transactionDto);
//        transactionDto.setAccount(savedTran.getAccount().getAccountNumber());
//        return transactionDto;
//    }
//
//    public TransactionDto withdrawTransaction(WithdrawRequest withdrawRequest) {
//        Account account = accountRepository.findAccountByAccountNumber(withdrawRequest.getAccountNumber()).
//                orElseThrow(() -> new AccountException("Account number not found"));
//        if (account.getBalance().compareTo(withdrawRequest.getAmount()) < 0) {
//            throw new AccountException("Insufficient funds");
//        }
//        Transaction transaction = new Transaction();
//        BeanUtils.copyProperties(withdrawRequest, transaction);
//        transaction.setAccount(account);
//        account.setBalance(account.getBalance().subtract(withdrawRequest.getAmount()));
//        accountRepository.save(account);
//        Transaction savedTran = transactionRepository.save(transaction);
//        TransactionDto transactionDto = new TransactionDto();
//        BeanUtils.copyProperties(savedTran, transactionDto);
//        transactionDto.setAccount(savedTran.getAccount().getAccountNumber());
//        return transactionDto;
//
//    }
//
//    public TransactionDto transferTransaction(TransferRequest transferRequest) {
//        Account toAccount = accountRepository.findAccountByAccountNumber(transferRequest.getToAccountNumber()).
//                orElseThrow(() -> new AccountException("Account number not found"));
//        Account fromAccount = accountRepository.findAccountByAccountNumber(transferRequest.getFromAccountNumber()).
//                orElseThrow(() -> new AccountException("Account number not found"));
//        if (fromAccount.getBalance().compareTo(transferRequest.getAmount()) < 0) {
//            throw new AccountException("Insufficient funds");
//        }
//        Transaction transaction = new Transaction();
//        BeanUtils.copyProperties(transferRequest, transaction);
//        toAccount.setBalance(toAccount.getBalance().add(transferRequest.getAmount()));
//        fromAccount.setBalance(fromAccount.getBalance().subtract(transferRequest.getAmount()));
//        accountRepository.save(fromAccount);
//        accountRepository.save(toAccount);
//        transaction.setAccount(fromAccount);
//        transaction.setFromAccount(fromAccount.getAccountNumber());
//        transaction.setToAccount(toAccount.getAccountNumber());
//        Transaction savedTran = transactionRepository.save(transaction);
//        TransactionDto transactionDto = new TransactionDto();
//        BeanUtils.copyProperties(savedTran, transactionDto);
//        transactionDto.setAccount(savedTran.getAccount().getAccountNumber());
//        transactionDto.setFromAccountNumber(fromAccount.getAccountNumber());
//        transactionDto.setToAccountNumber(toAccount.getAccountNumber());
//        return transactionDto;
//    }
//
//
////    public TransactionDto updateTransaction(TransactionDto transactionDto) {
////        Account account = accountRepository.findAccountByAccountNumber(transactionDto.getAccount()).orElseThrow(
////                () -> new RuntimeException("Account number not found")
////        );
////       Transaction savedTransaction = transactionRepository.findById(transactionDto.getId()).orElseThrow(
////                () -> new RuntimeException("Transaction id not found")
////        );
////       savedTransaction.setAmount(transactionDto.getAmount());
////       transactionRepository.save(savedTransaction);
////       transactionDto.setTransactionDate(savedTransaction.getTransactionDate());
////       transactionDto.setType(savedTransaction.getType());
////       return transactionDto;
////    }
//
//
//    //Ok
//    public String deleteTransaction(String id) {
//        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new AccountException("Transaction id not found"));
//        Account account = accountRepository.findAccountByAccountNumber(transaction.getAccount().getAccountNumber()).orElseThrow(() -> new AccountException("Account number not found"));
//        account.setBalance(account.getBalance().subtract(transaction.getAmount()));
//        accountRepository.save(account);
//        transactionRepository.deleteById(id);
//        return "Transaction id " + id + " deleted";
//    }
//
//    //withdraw & deposit
//    public TransactionInfoResponse getTransactionById(String id) {
//        Transaction transaction = transactionRepository.findById(id).orElseThrow(
//                () -> new AccountException("Transaction id not found"));
//        TransactionInfoResponse transactionInfoResponse = new TransactionInfoResponse();
//        BeanUtils.copyProperties(transaction, transactionInfoResponse);
//        Account account = accountRepository.findAccountByAccountNumber(transaction.getAccount().getAccountNumber()).
//                orElseThrow(() -> new AccountException("Account number not found"));
//        transactionInfoResponse.setAccount(account.getAccountNumber());
//        return transactionInfoResponse;
//    }
//
//    public List<TransactionDto> getTransactionsByAccountNumber(String accountNumber) {
//
//        Account account = accountRepository.findAccountByAccountNumber(accountNumber)
//                .orElseThrow(() -> new AccountException("Account number not found"));
//
//        List<Transaction> transactionList = transactionRepository.findTransactionListByAccount(account);
//        List<TransactionDto> transactionDtoList = transactionList.stream().map(transaction -> {
//            TransactionDto dto = new TransactionDto();
//            BeanUtils.copyProperties(transaction, dto);
//            dto.setAccount(account.getAccountNumber());
//            return dto;
//        }).toList();
//        return transactionDtoList;
//    }
//}
