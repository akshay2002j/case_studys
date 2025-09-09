package org.example.locks;

public class MyAccount {


    public static void main(String[] args) throws InterruptedException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.deposit((long)500);
        Thread sbiAtm = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is withdrawing the Amount");
            bankAccount.withdraw(100);
        });

        Thread icicAtm = new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is withdrawing the Amount");
            bankAccount.withdraw(200);
        });

        System.out.println(bankAccount.getBalance() + " Before withdraw");
        sbiAtm.start();
        icicAtm.start();
        Thread.sleep(9000);
        System.out.println(bankAccount.getBalance() + " After withdraw");


    }
}
