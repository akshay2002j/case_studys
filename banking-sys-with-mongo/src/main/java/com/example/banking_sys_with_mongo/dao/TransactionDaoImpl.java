package com.example.banking_sys_with_mongo.dao;

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
public class TransactionDaoImpl {

    @Autowired
    private DB mongoDB;

    public DBCollection getCollection() {
        return mongoDB.getCollection("transactions");
    }


    public Transaction makeTransaction(Transaction transaction) {
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

    public Transaction makeTransferTransaction(Transaction transaction) {
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

    public Transaction getTransactionById(String id) {
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

    public boolean deleteTransactionById(String id) {
        DBCollection collection = getCollection();
        BasicDBObject document = new BasicDBObject();
        document.put("_id", new ObjectId(id));
        WriteResult writeResult = collection.remove(document);
        return writeResult.wasAcknowledged();
    }

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return transactionList;
    }

    public Map<String,String> numberOfTransactionsByAccount(String accountNumber) {
        DBCollection collection = getCollection();
        //stage 1: $match
        BasicDBObject match = new BasicDBObject("$match", new BasicDBObject("account", accountNumber));
        // Stage 2: $group
        BasicDBObject groupFields = new BasicDBObject("_id", "$type");
        groupFields.put("count", new BasicDBObject("$sum", 1));
        BasicDBObject group = new BasicDBObject("$group", groupFields);
        // Build pipeline
        List<DBObject> pipeline = Arrays.asList(match, group);
        // Execute aggregation
        AggregationOutput output = collection.aggregate(pipeline);
        Map<String,String> map = new HashMap<>();
        output.results().forEach(r -> {
            map.put(r.get("_id").toString(), r.get("count").toString());
        });
        return map;
    }
}
