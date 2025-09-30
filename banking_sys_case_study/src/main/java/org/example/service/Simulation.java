package org.example.service;

import org.example.entity.User;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Simulation {



    public static void main(String[] args) {
        BankService bankService = new BankService();
        ArrayList<User> users = new ArrayList<>();
        ArrayList<Long> accountNumberList = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            users.add(new User("User " + i, i * 1000)); // balance = 1000, 2000, 3000 ...
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);

        //open account for 20 user

        for (User user : users) {
           Future<Long> accountNo = executor.submit(() -> {
                bankService.openAccount(user);
            },user.getAccountNo());
            try {

                if (accountNo.isDone()) {
                    if (accountNo.state() == Future.State.SUCCESS) {
                        accountNumberList.add(accountNo.get());
                    }
                }
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
//
//        executor.submit(() -> {
//           for(User user : users) {
//               bankService.openAccount(user);
//           }
//        });

        //get all register user
        executor.submit(bankService::getAllUsers);

        //deposit 100 rs to each user
        for (Long accountNumber : accountNumberList) {
            executor.submit(() -> {
                bankService.deposit(accountNumber, 100);
            });
        }


        //withdraw 100 from each user
        for (Long accountNumber : accountNumberList) {
         executor.submit(() -> {
                bankService.withdraw(accountNumber, 100);
            });
        }

        //transfer the amount from one user to another

        for (int i = 0; i < users.size() && i < accountNumberList.size(); i++) {
            int finalI = i;
            executor.submit(() -> {
                bankService.transfer(users.get(finalI).getAccountNo(), accountNumberList.get(finalI -accountNumberList.size()), 100);
            });

        }

        for (Long accountNumber : accountNumberList) {
            executor.submit(() -> {
                bankService.getAccountDetails(accountNumber);
            });
        }

        executor.shutdown();
    }

}
