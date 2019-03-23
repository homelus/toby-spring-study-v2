package jun.spring.ch1;

import jun.spring.ch1.ioc.POJO.Hello;
import jun.spring.ch1.ioc.POJO.Printer;
import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class CH_1_1 {

    @Test
    public void POJO클래스와_설정메타정보를_가지고_애플리케이션컨텍스트_만들기() {
        GenericApplicationContext ac = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(ac);
        reader.registerBean(Pojo.class, "pojo", Primary.class, Lazy.class);
        Pojo pojo = ac.getBean("pojo", Pojo.class);
        assertThat(pojo, is(notNullValue()));
    }

    @Test
    public void GENERIC_APPLICATION_CONTEXT_테스트() {
        GenericApplicationContext ac = new GenericApplicationContext();
        // 특정 포맷의 빈 설정 메타 정보를 읽어 Bean Definition 으로 변환하는 기능
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
        reader.loadBeanDefinitions("/genericApplicationContext.xml");
        ac.refresh();

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

        MatcherAssert.assertThat(ac.getBean("printer").toString(), CoreMatchers.is("Hello Spring"));
    }

    @Test
    public void GENERIC_XML_APPLICATION_CONTEXT_테스트() {
        GenericApplicationContext ac = new GenericXmlApplicationContext("/genericApplicationContext.xml");
        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

        MatcherAssert.assertThat(ac.getBean("printer").toString(), CoreMatchers.is("Hello Spring"));
    }

    @Test
    public void APPLICATION_CONTEXT_계층구조_적용_테스트() {
        // 루트 컨텍스트 생성
        ApplicationContext parent = new GenericXmlApplicationContext("/parentContext.xml");
        // 자식 컨텍스트 생성
        GenericApplicationContext child = new GenericApplicationContext(parent);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
        // 부모에게는 printer 가 있고 자식에게는 printer 가 없음, print 빈을 찾을 것인가?
        reader.loadBeanDefinitions("/childContext.xml");
        child.refresh();

        Printer printer = child.getBean("printer", Printer.class);
        MatcherAssert.assertThat(printer, CoreMatchers.is(notNullValue()));

        Hello hello = child.getBean("hello", Hello.class);
        MatcherAssert.assertThat(hello, CoreMatchers.is(notNullValue()));

        hello.print();
        MatcherAssert.assertThat(printer.toString(), CoreMatchers.is("Hello Child")); // 자신이 속한 컨텍스트의 빈을 받는다.(자신이 우선이다)
    }




    private static class Pojo {
        private String name;
    }


}
