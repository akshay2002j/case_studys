package com.example.banking_sys_with_mongo.dao;

import com.example.banking_sys_with_mongo.exception.DBException;
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

    /**
     * @param user save method takes the {@link User} for persisting in DB
     * @return it returns the saved instance of {@link User}
     * @author Akshay Jadhav
     */
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
            throw  new DBException(ExceptionType.BD_ERROR);
        }
    }

    /**
     * @param id this takes the valid userId which refers to MongoDB _id
     * @return the {@link User} object which is saved on DB
     * @author Akshay jadhav
     */
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

    /**
     * @param user the {@link User} object which is saved on DB
     * @return the {@link User} object which is updated in DB
     * @author Akshay Jadhav
     */
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
            throw  new DBException(ExceptionType.BD_ERROR);
        }

    }

    /**
     * @param id the valid user id which should be be in DB
     * @return {@code true} if the deletion was acknowledged by MongoDB, otherwise {@code false}.
     * @author Akshay Jadav
     */
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
            throw  new DBException(ExceptionType.BD_ERROR);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw  new RuntimeException(e.getMessage());
        }
    }

    public User getByEmail(String email) {
        DBCollection collection = this.getCollection();
        BasicDBObject query = new BasicDBObject();
        query.put("email",email);
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



}
