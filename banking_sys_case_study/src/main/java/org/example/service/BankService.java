package org.example.service;

import org.example.entity.User;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankService {

   private final HashMap<Long, User> accountsUserBankMap = new HashMap<>();

 private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
 private Lock readLock = readWriteLock.readLock();
 private Lock writeLock = readWriteLock.writeLock();

    public void openAccount(User user) {
        boolean acquired;
        try {
           acquired =  writeLock.tryLock(200, TimeUnit.MILLISECONDS);
           if(acquired) {
               if (accountsUserBankMap.containsKey(user.getAccountNo())) {
                   System.out.println("Account already exists!");
               } else {
                       accountsUserBankMap.putIfAbsent(user.getAccountNo(), user);
                       System.out.println("Account created!");
               }
           }
           else {
               System.out.println("Lock acquired try again to open account!..!");
           }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
            Thread.currentThread().interrupt();
        } finally {
            writeLock.unlock();
        }
    }

    ///deposit amount the To respective account
    public void deposit(Long accountNo, long amount){
        boolean acquired;
        try {
            acquired =  writeLock.tryLock(200, TimeUnit.MILLISECONDS);
            if(acquired) {
                if (!accountsUserBankMap.containsKey(accountNo)) {
                    System.out.println("Account doesn't exists!");

                } else {
                    User user = accountsUserBankMap.get(accountNo);
                    user.getBalance().getAndAdd(amount);
                    System.out.println("Deposited " + amount);
                }
            }
            else {
                System.out.println("Lock acquired try again to deposit account!..!");
            }
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
            Thread.currentThread().interrupt();
        }
        finally {
            writeLock.unlock();
        }
    }

    //withdraw the amount the account
    public void withdraw(Long accountNo, long amount){

        boolean acquired;
        try {
            acquired = writeLock.tryLock(200,TimeUnit.MILLISECONDS);
            if (!accountsUserBankMap.containsKey(accountNo)) {
                System.out.println("Account doesn't exists!");
            } else {
                User user = accountsUserBankMap.get(accountNo);
                user.getBalance().getAndAdd(-amount);
                System.out.println("Withdraw Successful!");
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
            Thread.currentThread().interrupt();
        }
        finally {
            writeLock.unlock();
        }

    }
    public void transfer(Long fromAccountNo, Long toAccountNo, long amount){
        if (!accountsUserBankMap.containsKey(fromAccountNo)) {
            System.out.println("Account doesn't exists!");
        }
        if (!accountsUserBankMap.containsKey(toAccountNo)) {
            System.out.println("Account doesn't exists of receiver!");
        }
        User fromUser =  accountsUserBankMap.get(fromAccountNo);
        User toUser =  accountsUserBankMap.get(toAccountNo);
        if (fromUser.getBalance().get() >= amount) {
            fromUser.getBalance().getAndAdd(-amount);
            toUser.getBalance().getAndAdd(amount);
            System.out.println("Transfer Successful!");
        }
        else {
            System.out.println("Transfer Failed Insufficient Balance!");
        }
    }

    public void getAllUsers() {
        synchronized (accountsUserBankMap) {
            accountsUserBankMap.forEach((accountNo, user) -> {
                System.out.println(user);
            });
        }
    }

    public void getAccountDetails(Long accountNo) {

        try {
            if (readLock.tryLock(200,TimeUnit.MILLISECONDS)) {

                if (!accountsUserBankMap.containsKey(accountNo)) {
                    System.out.println("Account doesn't exists!");
                } else {
                    User user = accountsUserBankMap.get(accountNo);
                    System.out.println("Account Holder Name:- " + user.getName());
                    System.out.println("Account Number:- " + user.getAccountNo());
                    System.out.println("Account Balance:- " + user.getBalance());
                }
            }
            else {
                System.out.println("Lock acquired try again to read account!..!");
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
        }
        finally {
            readLock.unlock();
        }
    }

}
