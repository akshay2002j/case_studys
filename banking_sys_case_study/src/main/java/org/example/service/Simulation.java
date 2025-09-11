package org.example.service;

import org.example.entity.User;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Simulation {



    public static void main(String[] args) {
        BankService bankService = new BankService();
        ArrayList<User> users = new ArrayList<>();

        for (int i = 1; i <= 100; i++) {
            users.add(new User("User " + i, i * 1000)); // Example: balance = 1000, 2000, 3000 ...
        }

        ExecutorService executor = Executors.newFixedThreadPool(10);

        //open account for 20 user
        for (User user : users) {
            executor.submit(() -> {
                bankService.openAccount(user);
            });
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
        for (User user : users) {
            executor.submit(() -> {
                bankService.deposit(user.getAccountNo(), 100);
            });
        }


        //withdraw 100 from each user
        for (User user : users) {
            executor.submit(() -> {
                bankService.withdraw(user.getAccountNo(), 100);
            });
        }

        //transfer the amount from one user to another
        List<User> reversedUsers = new ArrayList<>(users.reversed());
        int size = reversedUsers.size();
        for (int i = 0; i < users.size(); i++) {
            int finalI = i;
            executor.submit(() -> {
                bankService.transfer(users.get(finalI).getAccountNo(), reversedUsers.get(finalI - size).getAccountNo(), 100);
            });

        }

        for (User user : users) {
            executor.submit(() -> {
                bankService.getAccountDetails(user.getAccountNo());
            });

        }
    }

}
