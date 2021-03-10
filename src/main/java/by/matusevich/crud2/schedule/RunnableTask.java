package by.matusevich.crud2.schedule;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RunnableTask implements Runnable {

    private String message;

    public RunnableTask(String message) {
        this.message = message;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(25));
            System.out.println(new Date() + " Runnable Task with " + message
                    + " on thread " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
