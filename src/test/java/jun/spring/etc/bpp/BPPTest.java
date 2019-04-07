package jun.spring.etc.bpp;

import jun.spring.etc.ioc.POJO.Hello;
import jun.spring.etc.ioc.POJO.StringPrinter;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import java.util.Map;

public class BPPTest {

    @Test
    public void bppTest() {
        StaticApplicationContext ac = new StaticApplicationContext();
        ac.addBeanFactoryPostProcessor(new CustomBeanFactoryPostProcessor());
        ac.registerBeanDefinition("hello", new RootBeanDefinition(Hello.class));
        ac.registerBeanDefinition("hello1", new RootBeanDefinition(Hello.class));
        ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
        ac.registerBeanDefinition("printer2", new RootBeanDefinition(StringPrinter.class));

        Map<String, Hello> beansOfType = ac.getBeansOfType(Hello.class);
        System.out.println(beansOfType.size());

        ac.registerBeanDefinition("bpp", new RootBeanDefinition(CustomBeanPostProcessor.class));
        System.out.println("before hello getBean");
        Hello hello = ac.getBean("hello", Hello.class);
        System.out.println(hello.sayHello());
        System.out.println("after hello getBean");
        System.out.println("before Refresh");
        ac.refresh();
        System.out.println("after Refresh");
        ac.getBean("hello");


    }

    private static class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            for (String beanName : beanFactory.getBeanDefinitionNames()) {
                BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
                System.out.println(bd.getBeanClassName() + " 처리 완료");
            }
            System.out.println("빈팩토리 프로세싱!");
        }
    }

    private static class CustomBeanPostProcessor implements BeanPostProcessor{

        @Override
        public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
            System.out.println(beanName + " 빈 전에 processor");
            return bean;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            System.out.println(beanName + " 빈 후에 processor");
            return bean;
        }
    }

}
