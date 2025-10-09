package com.example.banking_sys_with_mongo.config;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:mongoDb.properties")
public class MongoDBConfig {

    @Value("${app.mongodb.port}")
    private String port;

    @Value("${app.mongodb.database}")
    private String database;

    @Bean
    public MongoClient mongoClient() {
        System.out.println("Connecting to MongoDB on port: " + port);
        int p = Integer.parseInt(this.port);
        return new MongoClient("localhost", p);
    }

    @Bean
    public DB mongoDB(MongoClient client) {
        System.out.println("Using MongoDB database: " + database);
        return client.getDB(this.database);
    }
}
