package org.example;

public class VolatileKeyWord {

      boolean RUNNING = true;

      void stop() {
        this.RUNNING = false;
    }
     void start() {

        while (RUNNING) {
            System.out.println("Hello World");
        }
    }

    public static void main(String[] args) throws InterruptedException {

    VolatileKeyWord volatileKeyWord = new VolatileKeyWord();
        Thread thread = new Thread(volatileKeyWord::start,"Thread-1");
        thread.start();
        Thread.sleep(50);
        volatileKeyWord.stop();
    }
}
