package jun.spring.ch1;

import jun.spring.ch1.annotation.AnnotatedHello;
import jun.spring.ch1.bean.AnnotatedHelloBean;
import jun.spring.ch1.config.AnnotatedHelloConfig;
import jun.spring.ch1.config.HelloConfig;
import jun.spring.ch1.ioc.POJO.Hello;
import jun.spring.ch1.service.HelloService_1;
import jun.spring.ch1.service.HelloService_2;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class CH_1_2_2_REGISTER_BEAN {

    /**
     * XML 태그 등록
     */

    /**
     * 네임스페이스와 전용 태그 등록
     */

    /**
     * 자동 인식을 이용한 빈 등록: 스테레오타입 애노테이션과 빈 스캐너
     */

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
