package jun.spring.ch1.enable.schedule;

import org.springframework.scheduling.annotation.Scheduled;

public class ScheduleRateService {

    /**
     * 2초 마다 실행되는데
     * 이전 작업의 종료와 상관없이 실행한다.
     */
    @Scheduled(fixedRate = 2_000)
    public void scheduleRateTask() {
        System.out.println("Thread name: " + Thread.currentThread().getName());
        System.out.println("Fixed rate task: " + System.currentTimeMillis() / 1000);
    }

}
