package jun.spring.ch1.config;

import jun.spring.ch1.bean.AnnotatedHelloBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotatedHelloConfig {

    @Bean
    public AnnotatedHelloBean annotatedHelloBean() {
        return new AnnotatedHelloBean();
    }

}
