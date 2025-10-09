package com.example.banking_sys_with_mongo.dao;

import com.example.banking_sys_with_mongo.model.Account;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private DB mongoDB;

    public DBCollection getCollection() {
        return mongoDB.getCollection("accounts");
    }

    @Override
    public Account saveAccount(Account account) {
        DBCollection  collection = getCollection();
        BasicDBObject document = new BasicDBObject();
        document.put("accountId", UUID.randomUUID().toString());
        document.put("accountNumber", account.getAccountNumber());
        document.put("user", account.getUser());
        document.put("balance", account.getBalance());
        document.put("updatedAt", LocalDateTime.now());
        document.put("createdAt", LocalDateTime.now());
      WriteResult writeResult =  collection.insert(document);
        return account;
    }

    @Override
    public Account findByAccountNumber(String accNumber) {
        return null;
    }

    @Override
    public List<Account> findAll() {
        return List.of();
    }

    @Override
    public void deleteByAccountNumber(Account account) {

    }
}
