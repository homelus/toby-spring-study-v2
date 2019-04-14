package jun.spring.ch1.profile.nestedPkg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 빈의 개수가 많지 않고 단순할 경우 추천
 */
@Configuration
public class MainConfig {

    @Configuration
    @Profile("dev")
    public static class DevConfig {}

    @Configuration
    @Profile("qa")
    public static class QaConfig {}

    @Configuration
    @Profile("real")
    public static class RealConfig {}
}

