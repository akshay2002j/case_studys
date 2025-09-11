package org.example.entity;

import java.util.concurrent.atomic.AtomicLong;

public class User {
  private static final AtomicLong accountGen = new AtomicLong(10000L);
  private String name;
  private Long accountNo;
  private  AtomicLong balance = new AtomicLong(0L);

    public User(String name,long balance) {
        this.name = name;
        this.accountNo = accountGen.incrementAndGet();
        this.balance.addAndGet(balance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(Long accountNo) {
        this.accountNo = accountNo;
    }

    public AtomicLong getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance.getAndAdd(balance);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", accountNo=" + accountNo +
                '}';
    }
}
