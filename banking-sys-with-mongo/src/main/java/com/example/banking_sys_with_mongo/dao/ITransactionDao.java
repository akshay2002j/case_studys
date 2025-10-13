package com.example.banking_sys_with_mongo.dao;

import com.example.banking_sys_with_mongo.model.Transaction;

import java.util.List;
import java.util.Map;

public interface ITransactionDao {
    Transaction makeTransaction(Transaction transaction);
    Transaction makeTransferTransaction(Transaction transaction);
    Transaction getTransactionById(String id);
    boolean deleteTransactionById(String id);
    List<Transaction> getAllTransactionByAccount(String accountNumber);
    Map<String,String> numberOfTransactionsByAccount(String accountNumber);
}
