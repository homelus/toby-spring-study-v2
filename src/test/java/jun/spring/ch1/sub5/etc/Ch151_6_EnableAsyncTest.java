package jun.spring.ch1.sub5.etc;

import jun.spring.ch1.enable.async.AsyncService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class Ch151_6_EnableAsyncTest {

    /**
     * 비동기 테스트
     *
     * 1. the return type is constrained to either 'void' or 'java.util.concurrent.Future'
     * 2. It must be applied to public methods only // IDE 문법 에러
     * 3. self-invocation won't work (calling the async method within the same class)
     */
    @Test
    public void 비동기_테스트() throws ExecutionException, InterruptedException {
        System.out.println("Invoking an asynchronous method thread: " + Thread.currentThread().getName());
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AsyncConfig.class);
        AsyncService asyncService = context.getBean(AsyncService.class);

        asyncService.asyncMethodWithVoidReturnType();

        Future<String> future = asyncService.asyncMethodWithFutureReturnType();

        while (true) {
            if (future.isDone()) {
                System.out.println("Result from asynchronous process: " + future.get());
                break;
            }

            System.out.println("Continue doing something else");
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println("main completed");
    }

    @Test
    public void 비동기_예외_테스트() {
        System.out.println("Invoking an asynchronous method thread: " + Thread.currentThread().getName());
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AsyncConfig.class);
        AsyncService asyncService = context.getBean(AsyncService.class);
        asyncService.nestedAsyncTest();
        System.out.println("main completed");
    }

    @Test
    public void 쓰레드풀_비동기_테스트() {
        System.out.println("Invoking an asynchronous method thread: " + Thread.currentThread().getName());
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AsyncConfig.class);
        AsyncService asyncService = context.getBean(AsyncService.class);
        asyncService.asyncMethodWithThreadPool();
        System.out.println("main completed");
    }

    @Configuration
    @EnableAsync
    static class AsyncConfig {
        @Bean
        public AsyncService asyncService() {
            return new AsyncService();
        }
        @Bean(name = "testThreadPoolExecutor")
        public Executor threadPoolExecutor() {
            return new ThreadPoolTaskExecutor();
        }
    }

}
