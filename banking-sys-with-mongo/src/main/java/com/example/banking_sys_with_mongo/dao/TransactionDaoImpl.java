package com.example.banking_sys_with_mongo.dao;

import com.example.banking_sys_with_mongo.exception.DBException;
import com.example.banking_sys_with_mongo.exception.ExceptionType;
import com.example.banking_sys_with_mongo.model.Transaction;
import com.example.banking_sys_with_mongo.model.TransactionType;
import com.mongodb.*;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class TransactionDaoImpl implements ITransactionDao {

    @Autowired
    private DB mongoDB;

    public DBCollection getCollection() {
        return mongoDB.getCollection("transactions");
    }

    /**
     * @param transaction transaction class object for deposit or withdraw from account
     * @return transaction that has taken place weather deposit or withdraw
     * @author Akshay Jadhav
     * @description this method makes the transaction it will we deposit or withdraw
     */
    public Transaction makeTransaction(Transaction transaction) {
        try {
            DBCollection collection = getCollection();
            BasicDBObject document = new BasicDBObject();
            document.put("account", transaction.getAccount());
            document.put("amount", transaction.getAmount());
            document.put("type", transaction.getType().name());
            document.put("transactionDate", transaction.getTransactionDate());
            collection.insert(document);
            transaction.setId(document.get("_id").toString());
            return transaction;
        }
        catch (MongoException e){
            throw new DBException(ExceptionType.BD_ERROR);
        }
    }
    /**
     * Inserts a new transfer transaction (between two accounts) into the collection.
     * This method stores additional fields like {@code toAccount} and {@code fromAccount}.
     * @param transaction the {@link Transaction} object containing transfer details.
     * @return the saved {@link Transaction} with its MongoDB _id field set.
     */
    public Transaction makeTransferTransaction(Transaction transaction) {
        try {
            DBCollection collection = getCollection();
            BasicDBObject document = new BasicDBObject();
            document.put("account", transaction.getAccount());
            document.put("amount", transaction.getAmount());
            document.put("type", transaction.getType().name());
            document.put("toAccount", transaction.getToAccount());
            document.put("fromAccount", transaction.getFromAccount());
            document.put("transactionDate", transaction.getTransactionDate());
            collection.insert(document);
            transaction.setId(document.get("_id").toString());
            return transaction;
        }
        catch (MongoException e){
        throw new DBException(ExceptionType.BD_ERROR);
        }
    }

    /**
     * Fetches a single transaction document by its unique ID.
     *
     * @param id the MongoDB document ID of the transaction.
     * @return a populated {@link Transaction} object if found, otherwise {@code null}.
     */
    public Transaction getTransactionById(String id) {

        try {
            DBCollection collection = getCollection();
            BasicDBObject document = new BasicDBObject();
            document.put("_id", new ObjectId(id));
            DBObject savedTran = collection.findOne(document);
            if (savedTran != null) {
                Transaction transaction = new Transaction();
                transaction.setId(savedTran.get("_id").toString());
                transaction.setAccount(savedTran.get("account").toString());
                Object amountObj = savedTran.get("amount");
                BigDecimal amount;
                if (amountObj instanceof Decimal128) {
                    amount = ((Decimal128) amountObj).bigDecimalValue();
                } else {
                    amount = new BigDecimal(amountObj.toString());
                }
                transaction.setAmount(amount);
                transaction.setTransactionDate(transaction.getTransactionDate());
                transaction.setType(TransactionType.valueOf(savedTran.get("type").toString()));
                transaction.setToAccount(savedTran.get("toAccount").toString());
                transaction.setFromAccount(savedTran.get("fromAccount").toString());
                transaction.setTransactionDate((Date) savedTran.get("transactionDate"));
                return transaction;
            }
            return null;
        }
        catch (MongoException e){
            throw new DBException(ExceptionType.BD_ERROR);
        }
    }
    /**
     * Deletes a transaction document by its MongoDB ID.
     * @param id the ID of the transaction document to delete.
     * @return {@code true} if the deletion was acknowledged by MongoDB, otherwise {@code false}.
     */
    public boolean deleteTransactionById(String id) {
        try {
            DBCollection collection = getCollection();
            BasicDBObject document = new BasicDBObject();
            document.put("_id", new ObjectId(id));
            WriteResult writeResult = collection.remove(document);
            return writeResult.wasAcknowledged();
        }
        catch (MongoException e){
            throw new DBException(ExceptionType.BD_ERROR);
        }
    }

    /**
     * Retrieves all transactions for a given account number.
     * This includes deposits, withdrawals, and transfer records.
     * @param accountNumber the account number to filter transactions.
     * @return a {@link List} of {@link Transaction} objects belonging to that account.
     */
    public List<Transaction> getAllTransactionByAccount(String accountNumber) {

        DBCollection collection = this.getCollection();
        BasicDBObject document = new BasicDBObject();
        document.put("account", accountNumber);
        DBCursor cursor = collection.find(document);
        List<Transaction> transactionList = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                DBObject savedTran = cursor.next();
                Transaction transaction = new Transaction();
                transaction.setId(savedTran.get("_id").toString());
                transaction.setAccount(savedTran.get("account").toString());
                Object amountObj = savedTran.get("amount");
                BigDecimal amount;
                if (amountObj instanceof Decimal128) {
                    amount = ((Decimal128) amountObj).bigDecimalValue();
                } else {
                    amount = new BigDecimal(amountObj.toString());
                }
                transaction.setAmount(amount);
                transaction.setTransactionDate(transaction.getTransactionDate());
                transaction.setType(TransactionType.valueOf(savedTran.get("type").toString()));
                if (savedTran.get("toAccount") !=null && savedTran.get("fromAccount") !=null){
                    transaction.setToAccount(savedTran.get("toAccount").toString());
                    transaction.setFromAccount(savedTran.get("fromAccount").toString());
                }
                transaction.setTransactionDate((Date) savedTran.get("transactionDate"));

                transactionList.add(transaction);
            }
        } catch (MongoException e) {
           throw new DBException(ExceptionType.BD_ERROR);
        } finally {
            cursor.close();
        }

        return transactionList;
    }

    /**
     * Performs an aggregation query to count the number of transactions by type
     * (e.g., WITHDRAW, DEPOSIT) for a given account.
     * @param accountNumber the account number to filter transactions.
     * @return a {@link Map} where the key is the transaction type and the value is the count.
     */
    public Map<String,String> numberOfTransactionsByAccount(String accountNumber) {
        try {
            DBCollection collection = getCollection();
            //stage 1: $match
            BasicDBObject match = new BasicDBObject("$match", new BasicDBObject("account", accountNumber).append("type", "WITHDRAW"));
            // Stage 2: $group
            BasicDBObject groupFields = new BasicDBObject("_id", "$type");
            groupFields.put("count", new BasicDBObject("$sum", 1));
            BasicDBObject group = new BasicDBObject("$group", groupFields);
            // Build pipeline
            List<DBObject> pipeline = Arrays.asList(match, group);
            // Execute aggregation
            AggregationOutput output = collection.aggregate(pipeline);
            Map<String, String> map = new HashMap<>();
            output.results().forEach(r -> {
                map.put(r.get("_id").toString(), r.get("count").toString());
            });
            return map;
        }
        catch (MongoException e){
            throw new DBException(ExceptionType.BD_ERROR);
        }
    }
}
