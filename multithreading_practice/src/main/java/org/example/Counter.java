package org.example;

public class Counter {

    private  int value = 0;


    public synchronized  void increment() {
        value++;

//        synchronized (this) {
//            value++;
//        }
    }

    public int getValue() {
        return value;
    }
}
