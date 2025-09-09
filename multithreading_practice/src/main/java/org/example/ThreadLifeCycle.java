package org.example;

public class ThreadLifeCycle {


    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(()->{
            System.out.println("RUNNING");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "thread t1");


        System.out.println(t1.getState());
        t1.start();
        System.out.println(t1.getState());
        Thread.sleep(100);

        System.out.println(t1.getState());
        t1.join();
        System.out.println(t1.getState());


    }


}
