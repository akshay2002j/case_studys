package org.example.communication;

import java.util.LinkedList;
import java.util.Queue;

public class ThreadCommunication {

    private final Queue<Integer> queue = new LinkedList<>();
    private final int CAPACITY = 5;

    public synchronized void produce(int item) throws InterruptedException {
        while (queue.size() == CAPACITY) {
            wait(); // wait until consumer consumes
        }
        queue.add(item);
        System.out.println("Produced: " + item);
        notifyAll(); // notify consumers
    }

    public synchronized int consume() throws InterruptedException {
        while (queue.isEmpty()) {
            wait(); // wait until producer produces
        }
        int item = queue.poll();
        System.out.println("Consumed: " + item);
        notifyAll(); // notify producers
        return item;
    }


    public static void main(String[] args) {

        ThreadCommunication tc = new ThreadCommunication();
        Runnable producer = ()->{
            for(int i=0;i<20;i++){
                try {
                    tc.produce(i);
                } catch (InterruptedException e) {

                }
            }
        };

        Runnable consumer = ()->{
            for(int i=0;i<10;i++){
                try {
                    tc.consume();
                } catch (InterruptedException e) {
                  //  Thread.currentThread().interrupt();
                }
            }
        };

        new Thread(producer).start();
        new Thread(consumer).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
        }
    }
}
