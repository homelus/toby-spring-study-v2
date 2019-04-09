package jun.spring.ch1.sub5;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Ch151_1_Ref {

    @Test
    public void 빈의_XML_역할_구분_기초테스트() {
        MockServletContext sc = new MockServletContext();
        XmlWebApplicationContext ac = new XmlWebApplicationContext();
        ac.setServletContext(sc);
        ac.setConfigLocation("ch1_5/spring-config.xml");
        ac.refresh();
        System.out.println(String.join(",", ac.getBeanDefinitionNames()));
    }

    @Test
    public void ANNOTATION_CONFIG_AC_의_BEAN_DEFINITION_과_SINGLETON_조회_테스트() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        System.out.println("-- 초기화 전");
        System.out.println("### Bean Definition names");
        System.out.println(String.join("\n", ac.getBeanDefinitionNames()));
        System.out.println("### Singleton names");
        System.out.println(String.join("\n", ac.getBeanFactory().getSingletonNames()));
        System.out.println("### BeanFactoryPostProcessors");
        System.out.println(ac.getBeanFactoryPostProcessors().stream()
                .map(BeanFactoryPostProcessor::getClass)
                .map(Class::toString)
                .collect(Collectors.joining("\n")));
        ac.refresh();
        System.out.println("-- 초기화 후");
        System.out.println("### Bean Definition names");
        System.out.println(String.join("\n", ac.getBeanDefinitionNames()));
        System.out.println("### Singleton names");
        System.out.println(String.join("\n", ac.getBeanFactory().getSingletonNames()));
        System.out.println("### BeanFactoryPostProcessors");
        System.out.println(ac.getBeanFactoryPostProcessors().stream()
                .map(BeanFactoryPostProcessor::getClass)
                .map(Class::toString)
                .collect(Collectors.joining("\n")));
    }

    @Test
    public void ANNOTATION_CONFIG_AC_의_초기화전_프로세서의_원클래스_테스트() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();

        List<BeanDefinition> bds = Arrays.asList(
                ac.getBeanDefinition("org.springframework.context.annotation.internalConfigurationAnnotationProcessor"),
                ac.getBeanDefinition("org.springframework.context.annotation.internalAutowiredAnnotationProcessor"),
                ac.getBeanDefinition("org.springframework.context.annotation.internalRequiredAnnotationProcessor"),
                ac.getBeanDefinition("org.springframework.context.annotation.internalCommonAnnotationProcessor"),
                ac.getBeanDefinition("org.springframework.context.event.internalEventListenerProcessor"),
                ac.getBeanDefinition("org.springframework.context.event.internalEventListenerFactory")
        );

        for (BeanDefinition bd : bds) {
            System.out.println(bd.getBeanClassName());
        }

        /**
         * BeanFactoryPostProcessor
         * @see org.springframework.context.annotation.ConfigurationClassPostProcessor
         * BeanPostProcessor
         * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
         * @see org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor
         * @see org.springframework.context.annotation.CommonAnnotationBeanPostProcessor
         * SmartInitializingSingleton
         * @see org.springframework.context.event.EventListenerMethodProcessor
         * EventListenerFactory
         * @see org.springframework.context.event.DefaultEventListenerFactory
         */
    }

    @Test
    public void CONTEXT_ANNOTAITON_CONFIG_BEAN_조회_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("ch1_5/annotation-config.xml");
        System.out.println("### Bean Definition names");
        System.out.println(String.join("\n", ac.getBeanDefinitionNames()));
        System.out.println("### Singleton names");
        System.out.println(String.join("\n", ac.getBeanFactory().getSingletonNames()));
    }

    @Test
    public void CONTEXT_COMPONENT_SCAN_BEAN_조회_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("ch1_5/component-scan.xml");
        System.out.println("### Bean Definition names");
        System.out.println(String.join("\n", ac.getBeanDefinitionNames()));
        System.out.println();
        System.out.println("### Singleton names");
        System.out.println(String.join("\n", ac.getBeanFactory().getSingletonNames()));
    }

}
