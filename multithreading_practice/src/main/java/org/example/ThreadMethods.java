package org.example;

public class ThreadMethods {


    public static void main(String[] args) throws InterruptedException {



        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
            }
        }, "MyCustom Thread 1");

//        t1.start();
//        System.out.println(t1.getState());
//        t1.interrupt();
//        System.out.println( "The Thread is Interrupted Status is" + t1.isInterrupted());
//        System.out.println(t1.getState());

       Thread t2 = new Thread(()->{

           System.out.println(Thread.currentThread().getName());
           try {
               //doing some work
               Thread.sleep(100);
               System.out.println(Thread.currentThread().getState()+ " state of " + Thread.currentThread().getName());
               System.out.println("In the Thread " + Thread.currentThread().getName() + " doing work");
           } catch (InterruptedException e) {

           }
       },"MyCustom Thread 2");



        Thread t3 = new Thread(()->{
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(100);
                Thread.yield();
                System.out.println(Thread.currentThread().getState()+ " state of " + Thread.currentThread().getName());
                System.out.println("In the Thread " + Thread.currentThread().getName() + " doing work");
            } catch (InterruptedException e) {

            }
        },"MyCustom Thread 3");

        t2.start();

        t3.start();


    }
}
