package org.example.executorframwork;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorPrac {


    public static void main(String[] args) {


        ExecutorService executorService = Executors.newFixedThreadPool(5);

        ThreadGroup threadGroup = new ThreadGroup("threadGroup");
        System.out.println(threadGroup.activeCount());



    }
}
