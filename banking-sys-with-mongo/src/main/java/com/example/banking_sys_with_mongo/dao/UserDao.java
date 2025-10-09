package com.example.banking_sys_with_mongo.dao;


import com.example.banking_sys_with_mongo.model.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Date;



@Repository
public class UserDao {

    @Autowired
    private DB mongoDB;

    public DBCollection getCollection() {
        return mongoDB.getCollection("users");
    }


    public User saveUser(User user) {
        DBCollection  collection = this.getCollection();
        BasicDBObject document = new BasicDBObject();
            document.put("name",user.getName());
            document.put("email",user.getEmail());
            document.put("password",user.getPassword());
            document.put("createdAt",new Date());
              collection.insert(document);
            user.setUserId(document.get("_id").toString());
        user.setCreatedAt((Date) document.get("createdAt"));
        return user;
    }
}
