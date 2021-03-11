package by.matusevich.crud2.schedule;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.lock.FencedLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MultithreadingService {

    private final HazelcastInstance hazelcastInstance;
    private final ThreadPoolTaskScheduler scheduler;


    FencedLock lock = null;

    public boolean tryLock(){
        lock=hazelcastInstance.getCPSubsystem().getLock("lock");
        log.error("initialising ");
        if (lock.isLocked()) {
            log.error("can not lock");
            return false;
        }
        scheduledThreadPool();
        return true;
    }

    @Async
    public void scheduledThreadPool() {
        try {
            log.error("tryLock()");
            scheduler.scheduleAtFixedRate(new RunnableTask("new task, with 25_000 rate", lock), 20000);
        }catch (Exception e){
            scheduler.scheduleAtFixedRate(new RunnableTask("new task, after_exception", lock), 20000);
        } finally {
            log.error("finally");
        }
    }

}
