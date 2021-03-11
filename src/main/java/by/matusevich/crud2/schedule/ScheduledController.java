package by.matusevich.crud2.schedule;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedule")
public class ScheduledController {

    private final MultithreadingService service;

    @GetMapping("/scheduler")
    public ResponseEntity<Boolean> schedulerExample() {
        log.info("calling scheduledThreadPool service");
        if (service.tryLock()){
            return ResponseEntity.ok(service.tryLock());
        }
        return ResponseEntity.ok(false);
    }
}
