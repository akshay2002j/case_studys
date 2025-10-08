package com.example.banking_sys_with_mongo.repository;

import com.example.banking_sys_with_mongo.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository  extends MongoRepository<Account,String> {
  void deleteAccountByAccountNumber(String accountNumber);
   Optional<Account> findAccountByAccountNumber(String accountNumber);
}
