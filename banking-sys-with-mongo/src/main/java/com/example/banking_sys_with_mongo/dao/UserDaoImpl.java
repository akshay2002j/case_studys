package com.example.banking_sys_with_mongo.dao;


import com.example.banking_sys_with_mongo.exception.ExceptionType;
import com.example.banking_sys_with_mongo.exception.UserException;
import com.example.banking_sys_with_mongo.model.User;
import com.mongodb.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Date;



@Repository
public class UserDaoImpl {

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

    public User getUserById(String id) {
        DBCollection collection = this.getCollection();
        BasicDBObject query = new BasicDBObject();
        query.put("_id", new ObjectId(id)); //It requires to  Convert String to ObjectId (L)
        DBObject result = collection.findOne(query);
        User user = new User();
        if (result != null) {
            user.setUserId(result.get("_id").toString());
            user.setName(result.get("name").toString());
            user.setEmail(result.get("email").toString());
            user.setPassword(result.get("password").toString());
            user.setCreatedAt((Date) result.get("createdAt"));
            return user;
        }
        throw new UserException(ExceptionType.USER_NOT_FOUND);
    }

    public User updateUser(User user) {
        DBCollection collection = this.getCollection();
        BasicDBObject document = new BasicDBObject();
        document.put("name",user.getName());
        document.put("email",user.getEmail());
        document.put("password",user.getPassword());
        document.put("createdAt",new Date());
        BasicDBObject updateDocument = new BasicDBObject();
        updateDocument.put("$set", document);
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(user.getUserId()));
        collection.update(query,updateDocument);
        return user;
    }

    public boolean deleteUser(String id) {
        DBCollection collection = this.getCollection();
        BasicDBObject document = new BasicDBObject();
        document.put("_id",new  ObjectId(id));
      WriteResult writeResult =  collection.remove(document);
      return writeResult.wasAcknowledged();
    }
}
