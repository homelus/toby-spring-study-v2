package jun.spring.ch1.enable.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class CacheService {

    public static int cacheCount = 0;

    @Autowired
    private CacheRepository cacheRepository;

    @Cacheable("test-cache")
    public List<Integer> getCachedSampleData(int key) {
        cacheCount++;
        System.out.println(cacheCount + " CacheService Called, param: " + key);
        return cacheRepository.getSampleData(key);
    }

    @CacheEvict(value = "test-cache")
    public List<Integer> evictCacheData(int key) {
        cacheCount++;
        System.out.println(key + " 캐시 삭제 후 조회, count: " + cacheCount);
        return cacheRepository.getSampleData(key);
    }

    @CacheEvict(value = "test-cache", allEntries = true)
    public void evictAllCache() {
        System.out.println("모든 캐시 삭제");
    }

    @CachePut(value = "test-cache")
    public List<Integer> putCache(int key) {
        System.out.println(key + " 값 캐시 수정");
        return Collections.singletonList(100);
    }

    @Cacheable(value="test-cache", key = "#car.key")
    public List<Integer> cacheCar(Car car) {
        System.out.println("Car 조회: " + car.key + " " + car.name);
        cacheCount++;
        return Collections.singletonList(5);
    }

    public static class Car {

        public Car(String key, String name) {
            this.key = key;
            this.name = name;
        }

        String key;
        String name;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
