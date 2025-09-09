package org.example;

public class Mythread extends Thread{
    String filename;
    public Mythread(String filename){
        this.filename=filename;
    }

    @Override
    public void run() {

        System.out.println("Start downloading file "+ filename);

        try {
            currentThread().sleep( 200);
            System.out.println("Downloading file "+ filename);
            System.out.println(currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("End downloading file "+ filename);
    }
}
