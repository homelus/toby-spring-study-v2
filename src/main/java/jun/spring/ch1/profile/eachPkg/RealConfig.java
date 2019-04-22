package jun.spring.ch1.profile.eachPkg;

import jun.spring.ch1.profile.PublicBean;
import jun.spring.ch1.profile.RealBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("real")
public class RealConfig {

    @Bean
    public RealBean realBean() {
        return new RealBean();
    }

    @Bean
    public PublicBean publicBean() {
        return new PublicBean();
    }

}
