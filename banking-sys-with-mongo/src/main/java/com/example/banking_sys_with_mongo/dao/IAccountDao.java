package com.example.banking_sys_with_mongo.dao;

import com.example.banking_sys_with_mongo.model.Account;
import com.mongodb.DBCollection;

public interface IAccountDao {
    DBCollection getCollection();
    Account save(Account account);
    Account findByAccountNumber(String accNumber);
    boolean deleteByAccountNumber(String accountNumber);
    Account updateAccount(Account account);
}
