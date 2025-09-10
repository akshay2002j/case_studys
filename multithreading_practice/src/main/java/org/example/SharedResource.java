package org.example;

public class SharedResource {
   volatile int count = 0;

    void increment() {
        this.count++;
    }
    int get() {
        return this.count;
    }
}
