package jun.spring.ch1.sub5;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.util.Arrays;
import java.util.List;

public class CH_1_5_1_BEAN_ROLE {

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
    public void 빈의_ANNOTATION_역할_구분_기초_테스트() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        System.out.println("-- 초기화 전");
        System.out.println("### Bean Definition names");
        System.out.println(String.join("\n", ac.getBeanDefinitionNames()));
        System.out.println("### Singleton names");
        System.out.println(String.join("\n", ac.getBeanFactory().getSingletonNames()));
        ac.refresh();
        System.out.println("-- 초기화 후");
        System.out.println("### Bean Definition names");
        System.out.println(String.join("\n", ac.getBeanDefinitionNames()));
        System.out.println("### Singleton names");
        System.out.println(String.join("\n", ac.getBeanFactory().getSingletonNames()));
    }

    @Test
    public void ANNOTATION_초기화전_기초_프로세서의_원클래스_테스트() {
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
         * @see org.springframework.context.annotation.ConfigurationClassPostProcessor
         * @see org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor
         * @see org.springframework.beans.factory.annotation.RequiredAnnotationBeanPostProcessor
         * @see org.springframework.context.annotation.CommonAnnotationBea nPostProcessor
         * @see org.springframework.context.event.EventListenerMethodProcessor
         * @see org.springframework.context.event.DefaultEventListenerFactory
         */

    }


}
