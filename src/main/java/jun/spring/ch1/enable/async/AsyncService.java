package jun.spring.ch1.enable.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class AsyncService {

    @Async
    public void asyncMethodWithVoidReturnType() {
        System.out.println("asynchronously Execute method thread: " + Thread.currentThread().getName());
    }

    @Async
    public Future<String> asyncMethodWithFutureReturnType() {
        System.out.println("asynchronously Execute method thread: " + Thread.currentThread().getName());

        try {
            TimeUnit.SECONDS.sleep(3);
            return new AsyncResult<>("Hello jun");
        } catch (InterruptedException ignore) {}

        return null;
    }

    public void nestedAsyncTest() {
        System.out.println("invoke method: " + Thread.currentThread().getName());
        asyncPrivateMethod();
    }

    @Async
    public void asyncPrivateMethod() {
        System.out.println("nested method: " + Thread.currentThread().getName());
    }

    @Async("testThreadPoolExecutor")
    public void asyncMethodWithThreadPool() {
        System.out.println("invoke async thread pool method: " + Thread.currentThread().getName());
    }

}
