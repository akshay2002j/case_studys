package org.example.service;

import org.example.constant.AccountErrorCode;
import org.example.entity.User;
import org.example.exception.BankOperationException;

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

    public Long openAccount(User user) {
        boolean acquired;
        try {
           acquired =  writeLock.tryLock(200, TimeUnit.MILLISECONDS);
           if(acquired) {
               if (accountsUserBankMap.containsKey(user.getAccountNo())) {
                   throw new BankOperationException(AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getCode(),AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getMessage());
               } else {
                       accountsUserBankMap.putIfAbsent(user.getAccountNo(), user);
                      System.out.println("Account created!");
               }
           }
           else {
               System.out.println("Lock acquired try again to open account!..!");
           }
        } catch (BankOperationException e) {
            System.out.println(e.getCode()+ " " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
        } finally {
            writeLock.unlock();
        }
        return user.getAccountNo();
    }

    ///deposit amount the To respective account
    public void deposit(Long accountNo, long amount){
        boolean acquired;
        try {
            acquired =  writeLock.tryLock(200, TimeUnit.MILLISECONDS);
            if(acquired) {
                if (!accountsUserBankMap.containsKey(accountNo)) {
                    throw new BankOperationException(AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getCode(),AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getMessage());
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
        catch (BankOperationException e) {
            System.out.println(e.getCode()+ " " + e.getMessage());
        }
        catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
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
                throw new BankOperationException(AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getCode(),AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getMessage());
            } else {
                User user = accountsUserBankMap.get(accountNo);
                if(user.getBalance().get()>=amount) {
                    user.getBalance().getAndAdd(-amount);
                    System.out.println("Withdraw Successful!");
                }else {
                    throw new BankOperationException(AccountErrorCode.ACCOUNT_BALANCE_NOT_ENOUGH.getCode(), AccountErrorCode.ACCOUNT_BALANCE_NOT_ENOUGH.getMessage());
                }
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
        } catch (BankOperationException e) {
            System.out.println(e.getCode()+ " " + e.getMessage());
        }
        finally {
            writeLock.unlock();
        }

    }
    public void transfer(Long fromAccountNo, Long toAccountNo, long amount)  {

        boolean acquired;
        try {
                acquired = writeLock.tryLock(200, TimeUnit.MILLISECONDS);
            if (!accountsUserBankMap.containsKey(fromAccountNo)) {
                throw new BankOperationException(AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getCode(),AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getMessage());
            }
            if (!accountsUserBankMap.containsKey(toAccountNo)) {
                System.out.println("Account doesn't exists of receiver!");
            }
            User fromUser = accountsUserBankMap.get(fromAccountNo);
            User toUser = accountsUserBankMap.get(toAccountNo);
            if (fromUser.getBalance().get() >= amount) {
                fromUser.getBalance().getAndAdd(-amount);
                toUser.getBalance().getAndAdd(amount);
                System.out.println("Transfer Successful!");
            } else {
                throw new BankOperationException(AccountErrorCode.ACCOUNT_BALANCE_NOT_ENOUGH.getCode(), AccountErrorCode.ACCOUNT_BALANCE_NOT_ENOUGH.getMessage());
            }
        } catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
        } catch (BankOperationException e) {
            System.out.println(e.getCode()+ " " + e.getMessage());
        }
        finally {
            writeLock.unlock();
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
                    throw new BankOperationException(AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getCode(),AccountErrorCode.ACCOUNT_ALREADY_EXISTS.getMessage());
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
        }catch (BankOperationException e) {
            System.out.println(e.getCode()+ " " + e.getMessage());
        }catch (InterruptedException e) {
            System.out.println("InterruptedException" + e.getMessage());
        }
        finally {
            readLock.unlock();
        }
    }

}
