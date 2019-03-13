package jun.spring.ch1.meta;

import jun.spring.ch1.annotation.AnnotatedHello;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class BeanScanningTest {

    @Test
    public void 기본_빈_스캐닝() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext("jun.spring.ch1.annotation"); // 빈스캐닝 패키지 지정
        AnnotatedHello hello = ctx.getBean("annotatedHello", AnnotatedHello.class); // 자동등록 빈 아이디는 첫 글자를 소문자로 바꿔서 사용

        assertThat(hello, is(notNullValue()));
    }

    @Test
    public void XML을_이용한_빈_스캐너_등록() {
        ApplicationContext ctx = new GenericXmlApplicationContext("meta/beanScannerWithXML.xml");
        AnnotatedHello hello = ctx.getBean("annotatedHello", AnnotatedHello.class);

        assertThat(hello, is(notNullValue()));
    }



}
