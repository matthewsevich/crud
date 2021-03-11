package by.matusevich.crud2.schedule;

import com.hazelcast.cp.lock.FencedLock;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RunnableTask implements Runnable {

    private String message;
    FencedLock lock;

    public RunnableTask(String message, FencedLock lock) {
        this.message = message;
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            lock.lock();
            log.info("locked");
            Thread.sleep(TimeUnit.SECONDS.toMillis(25));
            log.info(new Date() + " Runnable Task with " + message
                    + " on thread " + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isLockedByCurrentThread()) {
                lock.unlock();
                log.info("unlocked");
            }
        }

    }
}
