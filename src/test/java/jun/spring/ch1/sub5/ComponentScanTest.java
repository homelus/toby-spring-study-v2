package jun.spring.ch1.sub5;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ComponentScanTest {

    /**
     * 왜 NullPointerException 발생할까?
     */
    @Test(expected = NullPointerException.class)
    public void ComponentScan_없는_테스트() {
        ApplicationContext context = new GenericXmlApplicationContext("beanrole.xml");
        SimpleConfig sc = context.getBean(SimpleConfig.class);
        sc.hello.sayHello();
        fail();
    }

    @Test
    public void ComponentScan_없는_빈_정의_개수_확인_테스트() {
        GenericApplicationContext context = new GenericXmlApplicationContext("beanrole.xml");
        SimpleConfig sc = context.getBean(SimpleConfig.class);
        assertEquals(1, context.getBeanFactory().getSingletonNames().length);
    }

    /**
     * context:annotation-config 가 담당하는 Annotation 다음과 같은 어노테이션을 어떻게 처리할까?
     * _ @Configuration / @Bean / @Autowire / @PostStruct
     * 엄밀히 말하면 이는 context 네임 스페이스의 태그를 처리하는 핸드러를 통해 특정 빈이 등록되게 해준다.
     * 이 과정에서 등록되는 빈이 스프링 컨테이너를 확장해서 빈의 등록/관계설정/후처리 등에 새로운 기능을 부여한다.
     * 이 빈들을 컨테이너 인프라 빈이라고 한다.
     */
    @Test
    public void AnnotationConfig_있는_테스트() {
        ApplicationContext context = new GenericXmlApplicationContext("beanrole-with-componentScan.xml");
        SimpleConfig sc = context.getBean(SimpleConfig.class);
        sc.hello.sayHello();
    }

    @Test
    public void AnnotationConfig_있는_빈_정의_개수_테스트() {
        GenericApplicationContext context = new GenericXmlApplicationContext("beanrole-with-componentScan.xml");
        SimpleConfig sc = context.getBean(SimpleConfig.class);
        assertEquals(17, context.getBeanFactory().getSingletonNames().length);
        System.out.println(String.join("\n", context.getBeanFactory().getSingletonNames()));
    }

    static class SimpleConfig {

        @Autowired Hello hello;

        @Bean Hello hello() {
            return new Hello();
        }
    }

    static class Hello {
        @PostConstruct
        public void init() {
            System.out.println("Init");
        }

        public void sayHello() {
            System.out.println("Hello");
        }
    }


}
