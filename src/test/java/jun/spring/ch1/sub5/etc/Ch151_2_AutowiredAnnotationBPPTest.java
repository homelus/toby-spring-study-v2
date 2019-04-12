package jun.spring.ch1.sub5.etc;

import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertNotNull;

/**
 * BPP / BFPP 역할에 대한 테스트
 */
public class Ch151_2_AutowiredAnnotationBPPTest {

    @Test
    public void NotAutowiredAnnotationBeanPostProcessorTest() {
        DefaultListableBeanFactory dlbf = new DefaultListableBeanFactory();
        RootBeanDefinition rbd = new RootBeanDefinition(Person.class);
        dlbf.registerBeanDefinition("jun", rbd);
        Car car = new Car();
        dlbf.registerSingleton("car", car);

        Person person = (Person) dlbf.getBean("jun");
        assertNull(person.getCar());
    }

    /**
     * @see AbstractAutowireCapableBeanFactory#createBean(Class)
     * 호출 순서
     * 1. postProcessBeforeInstantiation
     *  - AbstractAutowireCapableBeanFactory.createBean -> resolveBeforeInstantiation -> applyBeanPostProcessorsBeforeInstantiation
     *  @see InstantiationAwareBeanPostProcessor 의 구현체
     * 2. determineCandidateConstructors
     *  - AbstractAutowireCapableBeanFactory.doCreateBean -> createBeanInstance -> determineConstructorsFromBeanPostProcessors
     *  @see SmartInstantiationAwareBeanPostProcessor 구현체
     * 3. postProcessMergedBeanDefinition
     *  - AbstractAutowireCapableBeanFactory.doCreateBean -> applyMergedBeanDefinitionPostProcessors
     *  @see MergedBeanDefinitionPostProcessor
     * 4. postProcessAfterInstantiation
     *  - AbstractAutowireCapableBeanFactory.doCreateBean -> populateBean
     *  @see InstantiationAwareBeanPostProcessor
     * 5. postProcessPropertyValues
     *  - AbstractAutowireCapableBeanFactory.doCreateBean -> populateBean
     *  @see InstantiationAwareBeanPostProcessor
     * 6. postProcessBeforeInitialization
     *  - AbstractAutowireCapableBeanFactory.doCreateBean -> initializeBean
     *  @see BeanPostProcessor
     * 7. postProcessAfterInitialization
     *  - AbstractAutowireCapableBeanFactory.doCreateBean -> initializeBean
     *  @see BeanPostProcessor
     */
    @Test
    public void AutowiredAnnotationBeanPostProcessorTest() {
        DefaultListableBeanFactory dlbf = new DefaultListableBeanFactory();
        AutowiredAnnotationBeanPostProcessor aabpp = new AutowiredAnnotationBeanPostProcessor();
        aabpp.setBeanFactory(dlbf);
        dlbf.addBeanPostProcessor(aabpp);

        RootBeanDefinition rbd = new RootBeanDefinition(Person.class);
        dlbf.registerBeanDefinition("jun", rbd);
        Car car = new Car();
        dlbf.registerSingleton("car", car);

        Person person = (Person) dlbf.getBean("jun");
        assertNotNull(person.getCar());
    }

    @Data
    private static class Person {
        @Autowired
        private Car car;
        private String name;
    }

    @Data
    private static class Car {
        private String name;
    }

}
