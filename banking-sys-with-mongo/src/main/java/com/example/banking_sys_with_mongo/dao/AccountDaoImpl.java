package com.example.banking_sys_with_mongo.dao;

import com.example.banking_sys_with_mongo.model.Account;
import com.mongodb.*;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;


@Repository
public class AccountDaoImpl implements IAccountDao {

    @Autowired
    private DB mongoDB;

    public DBCollection getCollection() {
        return mongoDB.getCollection("accounts");
    }

    /**
     * @param account object of the Account class
     * @return Account class object on successfully save operation
     * @author Akshay Jadhav
     * @description the method saves the account entity in the accounts mongo collection
     */
    @Override
    public Account save(Account account) {
        DBCollection collection = this.getCollection();
        BasicDBObject document = new BasicDBObject();
        document.put("accountNumber", account.getAccountNumber());
        document.put("user", account.getUser());
        document.put("balance", account.getBalance());
        document.put("updatedAt", LocalDateTime.now());
        document.put("createdAt", LocalDateTime.now());
        collection.insert(document);
        account.setAccountId(document.get("_id").toString());
        return account;
    }


    /**
     * @param accNumber valid account number of the user belong to the Account
     * @return Account class object on successfully find operation
     * @author Akshay Jadhav
     * @description finds the account with associated account number
     */
    @Override
    public Account findByAccountNumber(String accNumber) {
        DBCollection collection = this.getCollection();
        BasicDBObject document = new BasicDBObject();
        document.put("accountNumber", accNumber);
        DBObject result = collection.findOne(document);
        Account account = new Account();
        if (result != null) {
            account.setAccountId(result.get("_id").toString());
            account.setAccountNumber(accNumber);
            Object balanceObj = result.get("balance");
            BigDecimal balance;
            if (balanceObj instanceof Decimal128) {
                balance = ((Decimal128) balanceObj).bigDecimalValue();
            } else if (balanceObj instanceof Double) {
                balance = BigDecimal.valueOf((Double) balanceObj);
            } else {
                balance = new BigDecimal(balanceObj.toString());
            }
            account.setBalance(balance);
            account.setUser(result.get("user").toString());
            account.setCreatedAt((Date) result.get("createdAt"));
            account.setCreatedAt((Date) result.get("updatedAt"));
            return account;
        }
        return null;
    }

    /**
     * @param accNumber valid account number of the user belong to the Account
     * @return boolean value if deleted operation is successfully then true else false
     * @author Akshay Jadhav
     * @description deletes the account associated with account number
     */
    @Override
    public boolean deleteByAccountNumber(String accNumber) {
        DBCollection collection = this.getCollection();
        BasicDBObject document = new BasicDBObject();
        document.put("accountNumber", accNumber);
        WriteResult writeResult = collection.remove(document);
        return writeResult.wasAcknowledged();
    }

    /**
     * @param account
     * @return the updated account class object
     * @author Akshay Jadhav
     * @description updates the account class object depending on the changes received
     */
    public Account updateAccount(Account account) {
        DBCollection collection = this.getCollection();
        BasicDBObject document = new BasicDBObject();
        document.put("accountNumber", account.getAccountNumber());
        document.put("user", account.getUser());
        document.put("balance", account.getBalance());
        document.put("updatedAt", new Date());
        BasicDBObject updateDocument = new BasicDBObject("$set", document);
        BasicDBObject query = new BasicDBObject("_id", new ObjectId(account.getAccountId()));
        collection.update(query,updateDocument,false,false);
        return account;
    }


}
