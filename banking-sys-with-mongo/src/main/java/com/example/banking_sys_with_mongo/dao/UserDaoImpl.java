package com.example.banking_sys_with_mongo.dao;

import com.example.banking_sys_with_mongo.exception.ExceptionType;
import com.example.banking_sys_with_mongo.exception.UserException;
import com.example.banking_sys_with_mongo.model.User;
import com.mongodb.*;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;



@Slf4j
@Repository
public class UserDaoImpl implements IUserDao {

    @Autowired
    private DB mongoDB;

    public DBCollection getCollection() {
        return mongoDB.getCollection("users");
    }


    public User save(User user) {
        try {
            DBCollection collection = this.getCollection();
            BasicDBObject document = new BasicDBObject();
            document.put("name", user.getName());
            document.put("email", user.getEmail());
            document.put("password", user.getPassword());
            document.put("createdAt", new Date());
            collection.insert(document);
            user.setUserId(document.get("_id").toString());
            user.setCreatedAt((Date) document.get("createdAt"));
            return user;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw  new RuntimeException(e.getMessage());
        }
    }

    public User getById(String id) {
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
            else {
                throw new UserException(ExceptionType.USER_NOT_FOUND);
            }
    }

    public User update(User user) {
        try {
            DBCollection collection = this.getCollection();
            BasicDBObject document = new BasicDBObject();
            document.put("name", user.getName());
            document.put("email", user.getEmail());
            document.put("password", user.getPassword());
            document.put("createdAt", new Date());
            BasicDBObject updateDocument = new BasicDBObject();
            updateDocument.put("$set", document);
            BasicDBObject query = new BasicDBObject("_id", new ObjectId(user.getUserId()));
            collection.update(query, updateDocument);
            return user;
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw  new RuntimeException(e.getMessage());
        }

    }

    public boolean deleteById(String id) {

        try {
            DBCollection collection = this.getCollection();
            BasicDBObject document = new BasicDBObject();
            document.put("_id", new ObjectId(id));
            WriteResult writeResult = collection.remove(document);
            return writeResult.wasAcknowledged();
        }
        catch (MongoException me){
            log.error(me.getMessage());
            throw  new RuntimeException(me.getMessage());
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw  new RuntimeException(e.getMessage());
        }
    }


}
