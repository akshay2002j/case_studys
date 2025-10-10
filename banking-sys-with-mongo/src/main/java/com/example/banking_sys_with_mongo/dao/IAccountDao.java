package com.example.banking_sys_with_mongo.dao;

import com.example.banking_sys_with_mongo.model.Account;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

public interface IAccountDao {
    DBCollection getCollection();
    Account saveAccount(Account account);
    Account findByAccountNumber(String accNumber);
    List<Account> findAll();
    boolean deleteByAccountNumber(String accountNumber);
}
