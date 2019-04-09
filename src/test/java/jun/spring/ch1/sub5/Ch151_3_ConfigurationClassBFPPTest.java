package jun.spring.ch1.sub5;

import org.junit.Test;
import org.springframework.beans.factory.annotation.QualifierAnnotationAutowireCandidateResolver;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;

import static org.junit.Assert.assertSame;

public class Ch151_3_ConfigurationClassBFPPTest {

    @Test
    public void ConfigurationClassPostProcessorTest() {
        DefaultListableBeanFactory dlbf = new DefaultListableBeanFactory();
        QualifierAnnotationAutowireCandidateResolver acr = new QualifierAnnotationAutowireCandidateResolver();
        acr.setBeanFactory(dlbf);
        dlbf.setAutowireCandidateResolver(acr);
        dlbf.registerBeanDefinition("config", new RootBeanDefinition(BeanConfig.class));

        ConfigurationClassPostProcessor pp = new ConfigurationClassPostProcessor();
        pp.postProcessBeanFactory(dlbf); // 실행

        Foo foo = (Foo) dlbf.getBean("foo");
        Bar bar = (Bar) dlbf.getBean("bar");
        assertSame(foo, bar.foo);
    }

    @Configuration
    static class BeanConfig {
        @Bean
        public Foo foo() {
            return new Foo();
        }
        @Bean
        public Bar bar() {
            return new Bar(foo());
        }
    }

    static class Foo {}
    static class Bar {
        final Foo foo;
        public Bar(Foo foo) {
            this.foo = foo;
        }
    }

}
