package jun.spring.etc.ioc;

import jun.spring.etc.ioc.POJO.Hello;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.context.support.StaticApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApplicationContextTests {

    @Test
    public void STATIC_APPLICATION_CONTEXT_빈등록_테스트() {
        StaticApplicationContext ac = new StaticApplicationContext();
        System.out.println("Bean  Definition length: " + ac.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + ac.getBeanFactory().getSingletonCount());

        ac.registerSingleton("hello", Hello.class);
        System.out.println();
        System.out.println("Bean  Definition length: " + ac.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + ac.getBeanFactory().getSingletonCount());

        ac.getBean("hello");

        System.out.println();
        System.out.println("Bean  Definition length: " + ac.getBeanDefinitionNames().length);
        System.out.println("Singleton length: " + ac.getBeanFactory().getSingletonCount());
    }

    @Test
    public void genericApplicationContext_테스트() {
        GenericApplicationContext ac = new GenericApplicationContext();

        /**
         * 특정 포맷의 빈 설정 메타 정보를 읽어 Bean Definition 으로 변환하는 기능
         */
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
        reader.loadBeanDefinitions("/etc/genericApplicationContext.xml");

        ac.refresh();

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

        assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
    }

    @Test
    public void genericXmlApplicationContext_테스트() {
        /**
         * 생성자에 XML 파일 정보 설정 가능
         */
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/etc/genericApplicationContext.xml");

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

        assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
    }

}
