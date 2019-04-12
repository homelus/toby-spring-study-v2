package jun.spring.ch1.sub5.etc;

import jun.spring.ch1.enable.TestService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

public class Ch151_5_EnableTest {

    @Test
    public void aspectTest() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AspectJConfig.class);
        TestService testService = context.getBean(TestService.class);
        testService.test();
    }

    @Configuration
    @ComponentScan("jun.spring.ch1.enable")
    @EnableAspectJAutoProxy
    static class AspectJConfig {
    }

}
