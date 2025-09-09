package org.example;

public class DaemonThread {


    public static void main(String[] args) {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("Hello World");
                }
            }
        };


        Thread t = new Thread(r,"daemonThread");
        t.setDaemon(true);
        t.start();
        System.out.println("Main Done");
    }
}
