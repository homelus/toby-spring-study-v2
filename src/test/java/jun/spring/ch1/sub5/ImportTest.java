package jun.spring.ch1.sub5;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import static org.junit.Assert.assertNotNull;

public class ImportTest {

    @Test
    public void IMPORT_CONFIG_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        assertNotNull(context.getBean("myComponent"));
    }

    @Test
    public void IMPORT_RESOURCE_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ResourceConfig.class);
        assertNotNull(context.getBean("myComponent"));
    }

    @Configuration
    @ImportResource("classpath:bean-test.xml")

    static class ResourceConfig {}

    @Configuration
    @Import(InnerConfig.class)
    static class Config {

    }

    @Configuration
    @ComponentScan("jun.spring.ch1.component")
    static class InnerConfig {

    }

}
