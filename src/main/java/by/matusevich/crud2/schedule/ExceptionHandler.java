package by.matusevich.crud2.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        log.error("An exception has been captured\n");
        log.error("Thread: %s\n", t.getId());
        log.error("Exception: %s: %s\n", e.getClass().getName(), e.getMessage());
        log.error("Stack Trace: \n");
        e.printStackTrace(System.out);
        log.error("Thread status: %s\n", t.getState());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

}
