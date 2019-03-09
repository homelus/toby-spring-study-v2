package jun.spring.ch1;

import jun.spring.ch1.ioc.POJO.Hello;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ApplicationContextTest {

    @Test
    public void genericApplicationContext_테스트() {
        GenericApplicationContext ac = new GenericApplicationContext();

        /**
         * 특정 포맷의 빈 설정 메타 정보를 읽어 Bean Definition 으로 변환하는 기능
         */
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(ac);
        reader.loadBeanDefinitions("/genericApplicationContext.xml");

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
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/genericApplicationContext.xml");

        Hello hello = ac.getBean("hello", Hello.class);
        hello.print();

        assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
    }

}
