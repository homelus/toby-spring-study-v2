package jun.spring.ch1.profile.importPkg;

import jun.spring.ch1.profile.DevBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Bean
    public DevBean devBean() {
        return new DevBean();
    }
}
