package jun.spring.ch1;

import jun.spring.ch1.ioc.POJO.Hello;
import jun.spring.ch1.ioc.POJO.Printer;
import org.junit.Test;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class HierarchyContextTest {

    @Test
    public void 계층구조_CONTEXT_테스트() {
        // 루트 컨텍스트 생성
        ApplicationContext parent = new GenericXmlApplicationContext("/parentContext.xml");
        // 자식 컨텍스트 생성
        GenericApplicationContext child = new GenericApplicationContext(parent);
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(child);
        reader.loadBeanDefinitions("/childContext.xml"); // print 빈을 찾을 것인가?
        child.refresh();

        Printer printer = child.getBean("printer", Printer.class);
        assertThat(printer, is(notNullValue()));

        Hello hello = child.getBean("hello", Hello.class);
        assertThat(hello, is(notNullValue()));

        hello.print();
        assertThat(printer.toString(), is("Hello Child")); // 자신이 속한 컨텍스트의 빈을 받는다.(자신이 우선이다)
    }

}
