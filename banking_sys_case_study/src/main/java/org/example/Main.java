package org.example;

import org.example.entity.User;
import org.example.service.BankService;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();
        ExecutorService executor = Executors.newFixedThreadPool(5);

        boolean flag = true;
        do{
            System.out.println("----------Welcome to Bank Service---------");

            System.out.println("What do you want to do?");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer Money");
            System.out.println("5. Get all Users");
            System.out.println("6. Get Account Details");
            System.out.println("7. Exit");
             int choice = sc.nextInt();
             sc.nextLine();
             switch (choice){
                 case 1 -> {
                     System.out.println("Enter name");
                     String name = sc.nextLine();
                     System.out.println("Enter balance");
                     long balance = sc.nextLong();
                     bankService.openAccount(new User(name,balance));
                 }
                 case 2 -> {
                     System.out.println("Enter Account Number to Deposit");
                     Long accountNo = sc.nextLong();
                     System.out.println("Enter  Amount to Deposit");
                     long balance = sc.nextLong();
                     bankService.deposit(accountNo, balance);
                 }
                 case 3 -> {
                     System.out.println("Enter Account Number from which you want to withdraw");
                     Long accountNo = sc.nextLong();
                     System.out.println("Enter Account Amount to Withdraw");
                     long balance = sc.nextLong();
                     bankService.withdraw(accountNo, balance);
                 }
                 case 4 -> {
                     System.out.println("Enter Account Number to Transfer Money");
                     Long toAccountNo = sc.nextLong();
                     System.out.println("Enter Account from which to Transfer Money");
                     Long fromAccountNo = sc.nextLong();
                     System.out.println("Enter Amount to Transfer Money");
                     long amount = sc.nextLong();
                     bankService.transfer(fromAccountNo, toAccountNo, amount);
                 }
                 case 5 -> {
                     bankService.getAllUsers();
                 }
                 case  6 -> {
                     System.out.println("Enter account Number to Get Details");
                     Long accountNo = sc.nextLong();
                     bankService.getAccountDetails(accountNo);
                 }
                 case 7 -> {
                     flag = false;
                 }
             }

        }
        while (flag);

    }
}