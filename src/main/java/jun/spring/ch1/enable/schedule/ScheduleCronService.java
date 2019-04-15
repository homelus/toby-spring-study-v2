package jun.spring.ch1.enable.schedule;

import org.springframework.scheduling.annotation.Scheduled;

public class ScheduleCronService {

    @Scheduled(cron = "*/2 * * * * *")
    public void cronScheduleService() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        System.out.println("Fixed delay task: " + System.currentTimeMillis());
    }

}
