package org.example;

import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    volatile static int val = 0;

//    public static synchronized   void increment(){
//        val ++;
//    }
        public static    void increment(){
            synchronized ( Main.class ){
                val ++;
            }
    }



    public static void main(String[] args) throws InterruptedException {

        //thread creation with Thread class
//        Mythread downloadFileThread = new Mythread("json");
//        Mythread downloadFileThread1 = new Mythread("xml");
//
//        downloadFileThread.start();
//        downloadFileThread1.start();
//

        //2 thread creation with runnable interface
//        DownloadFile downloadFileJsonThread = new DownloadFile("json");
//        DownloadFile downloadFileXmlThread = new DownloadFile("xml");
//        new Thread(downloadFileJsonThread).start();
//        new Thread(downloadFileXmlThread).start();


        //creating thread without class with lambda function
        Runnable downloadpdfRunnable = ()->{
            System.out.println("Start thread to do task");
            try {
                Thread.sleep(200);
                System.out.println("To do task");
                System.out.println(Thread.currentThread().getPriority());
                System.out.println(Thread.currentThread().getId());
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(200);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("End thread to do task");
        };

        //this does not create new thread just main thread runs it
       // downloadpdfRunnable.run();

        //it creates new thread parallel to main thread and runs it
    // Thread t =    new Thread(downloadpdfRunnable,"downloadPDF Thread");
     //t.setPriority(Thread.NORM_PRIORITY);




        Thread t1 = new Thread(() -> {
            System.out.println("Start thread :-" +  Thread.currentThread().getName());
         //   System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(100);
                increment();
                System.out.println("value of variable in " + Thread.currentThread().getName() +" :- " +val);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "customThread 1");

        Thread t2 = new Thread(() -> {
            System.out.println("Start thread :-" +  Thread.currentThread().getName());
            //System.out.println(Thread.currentThread().getName());

            try {
                Thread.sleep(100);
                increment();
                System.out.println("value of variable in " + Thread.currentThread().getName() +" :- " +val);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "customThread 2");
        t1.setPriority(Thread.NORM_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t1.start();
        t2.start();
        t2.join();

        Thread.sleep(3000);
        System.out.println("value of variable after all operations:- " +val);
    }
}