package org.example.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {

    private Long balance = 0L;


    private final ReadWriteLock RWlock = new ReentrantReadWriteLock();

        Lock writeLock = RWlock.writeLock();
        Lock readLock = RWlock.readLock();

    public Long getBalance() {
        readLock.lock();
        try {
            return balance;
        }
        finally {
            readLock.unlock();
        }

    }

    void  deposit(Long amount) {
        this.balance += amount;
    }
//    void  withdraw(long amount) {
//        try {
//            if (writeLock.tryLock()) {
//                System.out.println("Lock acquired");
//                if (balance >= amount) {
//                    balance -= amount;
//                } else {
//                    System.out.println("Insufficient funds");
//                }
//            } else {
//                writeLock.unlock();
//                withdraw(amount);
//            }
//        }
//        finally {
//            System.out.println("Lock released");
//            writeLock.unlock();
//        }
//
//    }
    private final Lock lock = new ReentrantLock();

    void  withdraw(long amount)  {
        try {
            if (lock.tryLock(1000, TimeUnit.MILLISECONDS)) {
                try {
                    System.out.println("Acquiring the Lock");
                    if (balance >= amount) {
                        balance -= amount;
                        Thread.sleep(5000);
                    } else {
                        System.out.println("Insufficient funds");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("Unlocking the Lock");
                    lock.unlock();
                }

            } else {
                System.out.println("Lock has been acquired try one more time");
                lock.lock();
                withdraw(amount);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            lock.unlock();
        }
    }
}
