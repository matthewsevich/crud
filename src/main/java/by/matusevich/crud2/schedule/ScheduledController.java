package by.matusevich.crud2.schedule;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.cp.lock.FencedLock;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduledController {

    private final HazelcastInstance hazelcastInstance;
    private final ThreadPoolTaskScheduler scheduler;

    private static final int AMOUNT = 1;
    int count = 0;

//    @GetMapping("/scheduler")
//    public void schedulerExample() {
//
//        scheduler.scheduleAtFixedRate(new RunnableTask("new task, with 60_000 rate"),
//                60000
//        );
//
//        FencedLock lock = hazelcastInstance.getCPSubsystem().getLock("my-lock");
//        lock.lock();
//        while (lock.isLocked()) {
//            try {
//                Thread.sleep(TimeUnit.SECONDS.toMillis(25));
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("_______" +
//                    "locked" +
//                    "______");
//        }
//    }


    @Scheduled(cron = "0 " + AMOUNT + " 0 * * *")
    @GetMapping("/method")
    public void scheduledMethod() {
        System.out.println("_______" +
                "schedule" +
                "______");
        FencedLock lock = hazelcastInstance.getCPSubsystem().getLock("my-lock");
        lock.lock();
        while (lock.isLocked()) {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(25));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("_______" +
                    "locked" +
                    "______");
        }
        System.out.println(Thread.currentThread().getName() + " lock" +
                "_______");
        System.out.println(Thread.currentThread().getName());
    }
}
