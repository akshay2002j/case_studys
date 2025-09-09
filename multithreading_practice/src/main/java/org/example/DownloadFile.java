package org.example;


public class DownloadFile implements Runnable {
    String filcdename;
    public DownloadFile(String filename) {
        this.filename = filename;
    }
   public void run(){
       System.out.println("Start downloading file "+ filename);

       try {
           Thread.sleep( 200);
           System.out.println("Downloading file "+ filename);
       } catch (InterruptedException e) {
           throw new RuntimeException(e);
       }

       System.out.println("End downloading file "+ filename);
    }
}
