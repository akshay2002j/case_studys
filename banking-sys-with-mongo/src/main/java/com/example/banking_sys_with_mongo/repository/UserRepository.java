package com.example.banking_sys_with_mongo.repository;

import com.example.banking_sys_with_mongo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends MongoRepository<User,String> {

}
