package jun.spring.ch1;

import jun.spring.ch1.ioc.POJO.Customer;
import jun.spring.ch1.ioc.POJO.Deal;
import jun.spring.ch1.ioc.POJO.AnnotatedHelloBean;
import jun.spring.ch1.config.AnnotatedHelloConfig;
import jun.spring.ch1.config.HelloConfig;
import jun.spring.ch1.ioc.POJO.Hello;
import jun.spring.ch1.service.HelloService_1;
import jun.spring.ch1.service.HelloService_2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class CH_1_2_2_REGISTER_BEAN {

    /**
     * XML 태그 등록
     */
    @Test
    public void XML_이용하는_컨텍스트_테스트() {
        GenericApplicationContext ac = new GenericXmlApplicationContext("/xmlRegisterApplicationContext.xml");
        Hello hello = ac.getBean("hello", Hello.class);
        assertThat(hello, is(notNullValue()));
    }

    /**
     * 자동 인식을 이용한 빈 등록: 스테레오타입 애노테이션과 빈 스캐너
     * @see jun.spring.ch1.annotation.BusinessRule
     * @see Deal
     */
    @Test
    public void 빈_스캐닝_테스트() {
        ApplicationContext ac = new AnnotationConfigApplicationContext("jun.spring.ch1.annotation");
        Deal deal = ac.getBean("myDeal", Deal.class); // Bean 이름 변경하기
        Customer customer = ac.getBean("customer", Customer.class); // Custom Stereotype Annotation
        assertThat(deal, is(notNullValue()));
        assertThat(customer, is(notNullValue()));
    }

    /**
     * 자바 코드에 의한 빈 등록: @Configuration 클래스의 @Bean 메소드
     * @see org.springframework.context.annotation.Configuration
     * @see org.springframework.context.annotation.Bean
     */
    @Test
    public void CONFIGURATION_이용하는_컨텍스트_테스트() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AnnotatedHelloConfig.class);
        // 설정 클래스 내부의 @Bean 메소드들이 빈으로 저장된다.
        AnnotatedHelloBean hello = ctx.getBean("annotatedHelloBean", AnnotatedHelloBean.class);
        assertThat(hello, is(notNullValue()));

        // 설정 클래스 자체도 (빈 팩토리) 빈으로 저장된다.
        AnnotatedHelloConfig config = ctx.getBean("annotatedHelloConfig", AnnotatedHelloConfig.class);
        assertThat(config, is(notNullValue()));

        // 싱글톤 테스트
        // assertThat(config.annotatedHelloBean(), is(not(sameInstance(hello)))); // 실패
         assertThat(config.annotatedHelloBean(), is(sameInstance(hello)));
    }

    @Test
    public void CONFIGURATION_싱글톤_DI_테스트() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(HelloConfig.class);
        Hello hello = ctx.getBean("hello", Hello.class);
        Hello hello2 = ctx.getBean("hello2", Hello.class);

        assertThat(hello.getPrinter(), is(sameInstance(hello2.getPrinter())));
    }

    /**
     * 자바 코드에 의한 빈 등록: @Configuration 클래스를 사용안하는 @Bean 메소드
     * @see org.springframework.context.annotation.Bean
     */

    @Test
    public void CONFIGURATION_이용안하는_컨텍스트_테스트() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HelloService_1.class);
        Hello hello1 = ctx.getBean("hello1", Hello.class);
        Hello hello2 = ctx.getBean("hello2", Hello.class);

        assertThat(hello1.getPrinter(), is(not(sameInstance(hello2.getPrinter()))));
    }

    @Test
    public void CONFIGURATION_이용안하지만_SIGNLETON_주입_테스트() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(HelloService_2.class);
        Hello hello1 = ctx.getBean("hello1", Hello.class);
        Hello hello2 = ctx.getBean("hello2", Hello.class);

        assertThat(hello1.getPrinter(), is(sameInstance(hello2.getPrinter())));
    }


}
