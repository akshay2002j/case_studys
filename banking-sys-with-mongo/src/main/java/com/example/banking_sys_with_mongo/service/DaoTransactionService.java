package com.example.banking_sys_with_mongo.service;

import com.example.banking_sys_with_mongo.dao.AccountDaoImpl;
import com.example.banking_sys_with_mongo.dao.TransactionDaoImpl;
import com.example.banking_sys_with_mongo.dto.TransactionDto;
import com.example.banking_sys_with_mongo.dto.requestdto.DepositRequest;
import com.example.banking_sys_with_mongo.dto.requestdto.TransferRequest;
import com.example.banking_sys_with_mongo.dto.requestdto.WithdrawRequest;
import com.example.banking_sys_with_mongo.exception.AccountException;
import com.example.banking_sys_with_mongo.exception.ExceptionType;
import com.example.banking_sys_with_mongo.model.Account;
import com.example.banking_sys_with_mongo.model.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class DaoTransactionService {

    @Autowired
    TransactionDaoImpl transactionDao;
    @Autowired
    AccountDaoImpl accountDao;

    public TransactionDto makeDeposit(DepositRequest depositRequest){

        Account account =  accountDao.findByAccountNumber(depositRequest.getAccountNumber());

        if(account!= null){
            Transaction transaction = new Transaction();
            transaction.setAmount(depositRequest.getAmount());
            transaction.setType(depositRequest.getType());
            transaction.setAccount(account.getAccountNumber());
            transaction.setTransactionDate(new Date());
            account.setBalance(account.getBalance().add(transaction.getAmount()));
            accountDao.updateAccount(account);
            Transaction savedTran =  transactionDao.makeTransaction(transaction);
            TransactionDto transactionDto = new TransactionDto();
            BeanUtils.copyProperties(savedTran,transactionDto);
            return transactionDto;
        }
        else {
            throw new AccountException(ExceptionType.ACCOUNT_NOT_FOUND);
        }
    }

    public TransactionDto makeWithdraw(WithdrawRequest withdrawRequest){
        Account account =  accountDao.findByAccountNumber(withdrawRequest.getAccountNumber());

        if(account!= null){
            Transaction transaction = new Transaction();
            transaction.setAmount(withdrawRequest.getAmount());
            transaction.setType(withdrawRequest.getType());
            transaction.setAccount(account.getAccountNumber());
            transaction.setTransactionDate(new Date());
            if (account.getBalance().compareTo(withdrawRequest.getAmount()) >=0) {
                account.setBalance(account.getBalance().subtract(transaction.getAmount()));
                accountDao.updateAccount(account);
                Transaction savedTran = transactionDao.makeTransaction(transaction);
                TransactionDto transactionDto = new TransactionDto();
                BeanUtils.copyProperties(savedTran, transactionDto);
                return transactionDto;
            }
            else{
                throw new AccountException(ExceptionType.INSUFFICIENT_BALANCE);
            }
        }
        else {
            throw new AccountException(ExceptionType.ACCOUNT_NOT_FOUND);
        }
    }

    public TransactionDto makeTransfer(TransferRequest transferRequest){
        Account toaccount =  accountDao.findByAccountNumber(transferRequest.getToAccountNumber());
        Account fromaccount =  accountDao.findByAccountNumber(transferRequest.getFromAccountNumber());

        if(toaccount!= null && fromaccount != null){
            Transaction transaction = new Transaction();
            transaction.setAmount(transferRequest.getAmount());
            transaction.setType(transferRequest.getType());
            transaction.setAccount(fromaccount.getAccountNumber());
            transaction.setTransactionDate(new Date());
            transaction.setToAccount(transferRequest.getToAccountNumber());
            transaction.setFromAccount(transferRequest.getFromAccountNumber());
            if (fromaccount.getBalance().compareTo(transferRequest.getAmount()) >=0) {
                fromaccount.setBalance(fromaccount.getBalance().subtract(transaction.getAmount()));
                toaccount.setBalance(toaccount.getBalance().add(transaction.getAmount()));
                accountDao.updateAccount(toaccount);
                accountDao.updateAccount(fromaccount);
                Transaction savedTran = transactionDao.makeTransferTransaction(transaction);
                TransactionDto transactionDto = new TransactionDto();
                BeanUtils.copyProperties(savedTran, transactionDto);
                return transactionDto;
            }
            else{
                throw new AccountException(ExceptionType.INSUFFICIENT_BALANCE);
            }
        }
        else {
            throw new AccountException(ExceptionType.ACCOUNT_NOT_FOUND);
        }
    }


    public boolean cancelTransaction(String id){
       return transactionDao.deleteTransactionById(id);
    }

    public TransactionDto getTransactionById(String id){
        Transaction transaction =  transactionDao.getTransactionById(id);
        TransactionDto transactionDto = new TransactionDto();
        BeanUtils.copyProperties(transaction,transactionDto);
        transactionDto.setTransactionDate(transaction.getTransactionDate());
        return transactionDto;
    }

    public List<TransactionDto> getTransactionsByAccountNumber(String accountNumber) {
       List<Transaction> transactionList =  transactionDao.getAllTransactionByAccount(accountNumber);
       List<TransactionDto> transactionDtoList = transactionList.stream().map(transaction -> {
           TransactionDto transactionDto = new TransactionDto();
           BeanUtils.copyProperties(transaction,transactionDto);
           return transactionDto;
       }).toList();

       return transactionDtoList;
    }

    public Map<String,String> numberOfTransactionsByAccount(String accountNumber){
        return  transactionDao.numberOfTransactionsByAccount(accountNumber);
    }
}
