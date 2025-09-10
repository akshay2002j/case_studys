package org.example.executorframwork;

import java.util.concurrent.Callable;

public class CallableTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("CallableTask start");
        return "Hello World";
    }
}
