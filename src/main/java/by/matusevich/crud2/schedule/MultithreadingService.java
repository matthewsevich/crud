package by.matusevich.crud2.schedule;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.lock.FencedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MultithreadingService {

    @Autowired
    HazelcastInstance instance;
    @Qualifier("threadPoolTaskScheduler")
    @Autowired
    ThreadPoolTaskScheduler scheduler;


    public boolean tryLock() {
        FencedLock lock = instance.getCPSubsystem().getLock("lock");

        log.info("initialising ");
        if (lock.isLocked()) {
            log.info("can not lock");
            return false;
        }
        scheduledThreadPool(lock);
        return true;
    }

    @Async
    public void scheduledThreadPool(FencedLock lock) {
        try {
            log.info("tryLock()");
            scheduler.scheduleAtFixedRate(new RunnableTask("new task, with 25_000 rate", lock), 20000);
        } catch (Exception e) {
            scheduler.scheduleAtFixedRate(new RunnableTask("new task, after_exception", lock), 20000);
        } finally {
            log.info("finally");
        }
    }

}
