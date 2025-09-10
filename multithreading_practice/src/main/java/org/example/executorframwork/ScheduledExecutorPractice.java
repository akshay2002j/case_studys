package org.example.executorframwork;

import java.util.concurrent.*;

public class ScheduledExecutorPractice {


    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);

//        scheduledExecutorService.schedule(()->{
//            System.out.println("Task Scheduled after 5 seconds");
//        }, 5,TimeUnit.SECONDS);




//       Future<String> future =  scheduledExecutorService.schedule(
//                ()-> "This is callable task schedule after 5 seconds ",2,TimeUnit.SECONDS
//        );
//
//        try {
//            System.out.println( future.get());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        finally {
//            scheduledExecutorService.shutdown();
//        }


        scheduledExecutorService.scheduleAtFixedRate(
                ()->{
                    System.out.println("Scheduled task executed");
                } ,2,10,TimeUnit.SECONDS
        );

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        scheduledExecutorService.shutdown();
    }
}
