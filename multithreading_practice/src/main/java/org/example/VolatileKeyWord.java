package org.example;

public class VolatileKeyWord {



    public static void main(String[] args) throws InterruptedException {

       SharedResource sharedResource = new SharedResource();
        Thread thread1 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                sharedResource.increment();
            }
        },"worker-1");
        Thread thread2 = new Thread(()->{
            for (int i = 0; i < 100; i++) {
                sharedResource.increment();
            }
        },"worker-2");

        Thread thread3 = new Thread(()->{
          //  for (int i = 0; i < 200; i++) {
                System.out.println(  sharedResource.get());
           // }
        });
        thread1.start();
        thread2.start();
        thread3.start();

        Thread.sleep(100);
        //System.out.println(volatileKeyWord.count);
    }
}
