package jun.spring.ch1.enable.cache;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class CacheRepository {

    public List<Integer> getSampleData(int key) {
        System.out.println(CacheService.cacheCount + " CacheRepositoy Called");
        switch (key) {
            case 1:
                return Arrays.asList(1, 2, 3, 4, 5);
            case 10:
                return Arrays.asList(10, 20, 30, 40, 50);
            default:
                return Collections.singletonList(-1);
        }
    }

}
