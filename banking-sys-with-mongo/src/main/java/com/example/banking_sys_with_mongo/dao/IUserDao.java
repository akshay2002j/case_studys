package com.example.banking_sys_with_mongo.dao;

import com.example.banking_sys_with_mongo.model.User;

public interface IUserDao {
    User save(User user);
    User getById(String id);
    User update(User user);
    boolean deleteById(String id);
}
