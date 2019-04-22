package jun.spring.ch1.profile.nestedPkg;

import jun.spring.ch1.profile.DevBean;
import jun.spring.ch1.profile.PublicBean;
import jun.spring.ch1.profile.QABean;
import jun.spring.ch1.profile.RealBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 빈의 개수가 많지 않고 단순할 경우 추천
 */
@Configuration
public class NestedMainConfig {

    @Configuration
    @Profile("dev")
    public static class DevConfig {

        @Bean
        public DevBean devBean() {
            return new DevBean();
        }

    }

    @Configuration
    @Profile("qa")
    public static class QaConfig {

        @Bean
        public QABean qaBean() {
            return new QABean();
        }

        @Bean
        public PublicBean publicBean() {
            return new PublicBean();
        }

    }

    @Configuration
    @Profile("real")
    public static class RealConfig {

        @Bean
        public RealBean realBean() {
            return new RealBean();
        }

        @Bean
        public PublicBean publicBean() {
            return new PublicBean();
        }

    }
}

