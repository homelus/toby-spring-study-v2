package jun.spring.ch1.sub5.etc;

import jun.spring.ch1.enable.cache.CacheService;
import jun.spring.ch1.enable.cache.CacheService.Car;
import org.junit.Test;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static jun.spring.ch1.enable.cache.CacheService.cacheCount;
import static junit.framework.TestCase.assertEquals;

public class Ch151_8_EnableCachingTest {

    @Test
    public void 캐시_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CachingConfig.class);
        CacheService service = context.getBean(CacheService.class);
        service.getCachedSampleData(1);
        assertEquals(1, cacheCount);
        service.getCachedSampleData(1);
        assertEquals(1, cacheCount);
        service.getCachedSampleData(1);
        assertEquals(1, cacheCount);
        service.getCachedSampleData(10);
        assertEquals(2, cacheCount);
        service.getCachedSampleData(10);
        assertEquals(2, cacheCount);
        service.getCachedSampleData(2);
        assertEquals(3, cacheCount);
        service.getCachedSampleData(3);
        assertEquals(4, cacheCount);
        service.getCachedSampleData(1);
        assertEquals(4, cacheCount);
        service.getCachedSampleData(2);
        assertEquals(4, cacheCount);
    }

    @Test
    public void 캐시_소멸_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CachingConfig.class);
        CacheService service = context.getBean(CacheService.class);
        service.getCachedSampleData(1);
        assertEquals(1, cacheCount);
        service.getCachedSampleData(1);
        assertEquals(1, cacheCount);

        service.evictCacheData(10);
        assertEquals(2, cacheCount);

        service.getCachedSampleData(1);
        assertEquals(2, cacheCount);
        service.getCachedSampleData(2);
        assertEquals(3, cacheCount);
        service.getCachedSampleData(2);
        assertEquals(3, cacheCount);

        service.evictCacheData(1);
        assertEquals(4, cacheCount);

        service.getCachedSampleData(1);
        assertEquals(5, cacheCount);
        service.getCachedSampleData(1);
        assertEquals(5, cacheCount);

        service.evictAllCache();

        service.getCachedSampleData(1);
        assertEquals(6, cacheCount);
        service.getCachedSampleData(2);
        assertEquals(7, cacheCount);
    }

    @Test
    public void 캐시_업데이트_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CachingConfig.class);
        CacheService service = context.getBean(CacheService.class);
        service.getCachedSampleData(1);
        assertEquals(1, cacheCount);
        List<Integer> cachedSampleData = service.getCachedSampleData(1);
        assertEquals(1, cacheCount);
        assertEquals(cachedSampleData.size(), 5);

        service.putCache(1);

        List<Integer> updatedData = service.getCachedSampleData(1);
        assertEquals(1, updatedData.size());
    }

    @Test
    public void 객체_캐시_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CachingConfig.class);
        CacheService service = context.getBean(CacheService.class);

        service.cacheCar(new Car("1", "a"));
        assertEquals(1, cacheCount);
        service.cacheCar(new Car("1", "a"));
        assertEquals(1, cacheCount);
        service.cacheCar(new Car("1", "b"));
        assertEquals(1, cacheCount);

        // 캐시 안됌
        service.cacheCar(new Car("2", "a"));
        assertEquals(2, cacheCount);
    }

    @Configuration
    @EnableCaching
    @ComponentScan("jun.spring.ch1.enable.cache")
    static class CachingConfig {
        /**
         * 캐시는 반드시 CacheManager 가 등록되어야 한다.
         */
        @Bean
        public CacheManager cacheManager() {
            return new ConcurrentMapCacheManager("test-cache");
        }
    }

}
