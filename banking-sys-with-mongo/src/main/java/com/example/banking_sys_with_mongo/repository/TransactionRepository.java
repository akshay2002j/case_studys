package com.example.banking_sys_with_mongo.repository;

import com.example.banking_sys_with_mongo.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {
}
