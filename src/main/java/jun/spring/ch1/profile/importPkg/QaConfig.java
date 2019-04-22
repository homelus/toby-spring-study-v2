package jun.spring.ch1.profile.importPkg;

import jun.spring.ch1.profile.PublicBean;
import jun.spring.ch1.profile.QABean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("qa")
public class QaConfig {

    @Bean
    public QABean qaBean() {
        return new QABean();
    }

    @Bean
    public PublicBean publicBean() {
        return new PublicBean();
    }

}
