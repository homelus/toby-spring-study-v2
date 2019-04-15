package jun.spring.ch1.sub5.etc;

import jun.spring.ch1.enable.schedule.ScheduleCronService;
import jun.spring.ch1.enable.schedule.ScheduleDelayService;
import jun.spring.ch1.enable.schedule.ScheduleExceptionService;
import jun.spring.ch1.enable.schedule.ScheduleRateService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.TimeUnit;

public class Ch151_7_EnableScheduleTest {

    /**
     * 스케줄 테스트
     *
     * void return type
     * The annotated method must expect no arguments
     */

    @Test
    public void 이전작업_연관_스케줄_실행_테스트() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " started");
        System.out.println("main: " + System.currentTimeMillis());
        new AnnotationConfigApplicationContext(ScheduleDelayConfig.class);

        TimeUnit.SECONDS.sleep(10);
        System.out.println("main completed");
    }

    @Test
    public void 이전작업_연관없는_스케줄_실행_테스트() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " started");
        System.out.println("main: " + System.currentTimeMillis());
        new AnnotationConfigApplicationContext(ScheduleRateConfig.class);

        TimeUnit.SECONDS.sleep(10);
        System.out.println("main completed");
    }

    @Test
    public void 스케줄_크론_테스트() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " started");
        System.out.println("main: " + System.currentTimeMillis());
        new AnnotationConfigApplicationContext(ScheduleCronConfig.class);

        TimeUnit.SECONDS.sleep(10);
        System.out.println("main completed");
    }

    @Test
    public void 스케줄_예외_테스트() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " started");
        System.out.println("main: " + System.currentTimeMillis());
        new AnnotationConfigApplicationContext(ScheduleExceptionConfig.class);

        TimeUnit.SECONDS.sleep(10);
        System.out.println("main completed");
    }

    @Configuration
    @EnableScheduling
    static class ScheduleDelayConfig {
        @Bean
        public ScheduleDelayService scheduleDelayService() {
            return new ScheduleDelayService();
        }
    }

    @Configuration
    @EnableScheduling
    static class ScheduleRateConfig {
        @Bean
        public ScheduleRateService scheduleRateService() {
            return new ScheduleRateService();
        }
    }

    @Configuration
    @EnableScheduling
    static class ScheduleCronConfig {
        @Bean
        public ScheduleCronService scheduleCronService() {
            return new ScheduleCronService();
        }
    }

    @Configuration
    @EnableScheduling
    static class ScheduleExceptionConfig {
        @Bean
        public ScheduleExceptionService scheduleExceptionService() {
            return new ScheduleExceptionService();
        }
    }

}
