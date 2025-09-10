package org.example.executorframwork;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CallableTask callableTask = new CallableTask();

        ExecutorService executorService = Executors.newFixedThreadPool(1);

       Future<String> future =executorService.submit(callableTask);
        System.out.println("FutureTask state:- " + future.state());
//        try {
//            future.cancel(true);
//            System.out.println("FutureTask state:- " + future.state());
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//        finally {
//            executorService.shutdown();
//        }

        System.out.println(future.get());  //Waits if necessary for the computation to complete, and then retrieves its result.
       // future.get(1,TimeUnit.SECONDS); -->waits foe max this time
        System.out.println("FutureTask state:- " + future.state());
        System.out.println("main end");



        Future<String> runnableFuture=  executorService.submit(()->{
            System.out.println("FutureTask state:- " + future.state());
        },"success");
        System.out.println("runnableFuture result:- " + runnableFuture.get());


        Callable<Integer> cal1 = ()->{
            System.out.println("Task 1");
            return 1;
        };
        Callable<Integer> cal2 = ()->{
            System.out.println("Task 2");
            return 2;
        };
        Callable<Integer> cal3 = ()->{
            System.out.println("Task 3");
            return 3;
        };

        List<Callable<Integer>> list = List.of(cal1,cal2,cal3);
       List<Future<Integer>> listFuture =   executorService.invokeAll(list);
       for (Future<Integer> f:  listFuture) {
           System.out.println(f.get());
       }


        executorService.shutdown();
    }
}
