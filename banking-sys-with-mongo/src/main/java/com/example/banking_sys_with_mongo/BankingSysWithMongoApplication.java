package com.example.banking_sys_with_mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class BankingSysWithMongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSysWithMongoApplication.class, args);
	}

}
