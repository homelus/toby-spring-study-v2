package jun.spring.ch1.enable.schedule;

import org.springframework.scheduling.annotation.Scheduled;

public class ScheduleExceptionService {

    @Scheduled(fixedDelay = 2_000)
    public String privateSchedule() {
        System.out.println("Fixed delay task: " + System.currentTimeMillis());
        return "test";
    }

//    @Scheduled(fixedDelay = 2_000)
    public void parameterSchedule(String param) {
        System.out.println("param: " + param);
        System.out.println("Fixed delay task: " + System.currentTimeMillis());
    }

}
