package jun.spring.ch1.enable.schedule;

import org.springframework.scheduling.annotation.Scheduled;

public class ScheduleDelayService {

    /**
     * 2 초 마다 실행되는데,
     * 이전 작업이 끝나야 다음 작업을 실행한다.
     */
    @Scheduled(fixedDelay = 2_000)
    public void scheduleFixedDelayTask() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        System.out.println("Fixed delay task: " + System.currentTimeMillis());
    }

}
