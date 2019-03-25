package jun.spring.ch1.ioc;

import jun.spring.ch1.ioc.POJO.Hello;
import jun.spring.ch1.ioc.POJO.HelloFactory;
import jun.spring.ch1.ioc.POJO.HelloFactoryBean;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class BeanFactoryTests {

    @Test
    public void DLF_APPLICATION_CONTEXT_빈등록_테스트() {
        DefaultListableBeanFactory dlf = new DefaultListableBeanFactory();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());

        dlf.registerSingleton("hello", Hello.class);
        System.out.println();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());

        dlf.getBean("hello");
        dlf.getBean("a");

        System.out.println();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());
    }

    @Test
    public void DLF_FACTORY_BEAN_싱글톤_등록_테스트() {
        DefaultListableBeanFactory dlf = new DefaultListableBeanFactory();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());

        dlf.registerSingleton("helloBean", HelloFactoryBean.class);
        System.out.println();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());
        for (String singletonName : dlf.getSingletonNames()) {
            System.out.println(singletonName);
        }
        Object helloFactoryBean = dlf.getBean("helloBean"); // 문제가 있음. Casting

        System.out.println();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());
    }

    @Test
    public void DLF_FACTORY_BEAN_DEFINITION_등록_테스트() {
        DefaultListableBeanFactory dlf = new DefaultListableBeanFactory();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());

        dlf.registerBeanDefinition("helloBean", new RootBeanDefinition(HelloFactoryBean.class));
        System.out.println();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());
        for (String singletonName : dlf.getSingletonNames()) {
            System.out.println(singletonName);
        }

        Hello hello = dlf.getBean("helloBean", Hello.class); // 문제가 있음. Casting

        System.out.println();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());

        for (String singletonName : dlf.getSingletonNames()) {
            System.out.println(singletonName);
        }
    }

    @Test
    public void DLF_BEAN_DEFINITION_등록_테스트() {
        DefaultListableBeanFactory dlf = new DefaultListableBeanFactory();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());

        dlf.registerBeanDefinition("hello", new RootBeanDefinition(Hello.class));
        System.out.println();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());

        dlf.getBean("hello");

        System.out.println();
        System.out.println("Bean  Definition length: " + dlf.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + dlf.getSingletonCount());
    }

}
