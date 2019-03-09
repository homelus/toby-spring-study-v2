package jun.spring.ch1.ioc;

import com.sun.deploy.util.StringUtils;
import jun.spring.ch1.ioc.printer.POJO.Hello;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsNot.not;
import static org.junit.Assert.assertThat;

public class ApplicationContextTest {

    @Test
    public void BEAN_DEFINITION_빈등록_테스트() {

        StaticApplicationContext ac = new StaticApplicationContext();
        ac.registerSingleton("hello1", Hello.class); // DEFAULT 메타 정보를 이용 빈 등록

        Hello hello1 = ac.getBean("hello1", Hello.class);
        assertThat(hello1.sayHello(), is("Hello null"));
        assertThat(hello1, is(notNullValue()));
        BeanDefinition helloDef = new RootBeanDefinition(Hello.class); // 빈 메타정보를 담은 오브젝트(클래스 설정)
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");  // 빈의 name 에 프로퍼티 주입(프로퍼티 설정)
        ac.registerBeanDefinition("hello2", helloDef); // 빈 등록(아이디 설정)
        System.out.println(ac.getBeanFactory().getSingletonCount());

        Hello hello2 = ac.getBean("hello2", Hello.class);
        assertThat(hello1, is(not(hello2)));

        assertThat(ac.getBeanFactory().getBeanDefinitionCount(), is(2));
    }

    @Test
    public void BEAN_DEFINITION_빈등록_생성개수_테스트() {

        StaticApplicationContext ac = new StaticApplicationContext();
        System.out.println("[Singleton Cnt] before register hello1: " + ac.getBeanFactory().getSingletonCount());
        System.out.println("[BeanDefinition Cnt] before register hello1: " + ac.getBeanFactory().getBeanDefinitionCount());
        ac.registerSingleton("hello1", Hello.class); // DEFAULT 메타 정보를 이용 빈 등록
        System.out.println("[Singleton Cnt] after register hello1: " + ac.getBeanFactory().getSingletonCount());
        System.out.println("[BeanDefinition Cnt] after register hello1: " + ac.getBeanFactory().getBeanDefinitionCount());

        Hello hello1 = ac.getBean("hello1", Hello.class);
        System.out.println("[Singleton Cnt] get hello1: " + ac.getBeanFactory().getSingletonCount());
        System.out.println("[BeanDefinition Cnt] get hello1: " + ac.getBeanFactory().getBeanDefinitionCount());
        assertThat(hello1.sayHello(), is("Hello null"));
        assertThat(hello1, is(notNullValue()));
        BeanDefinition helloDef = new RootBeanDefinition(Hello.class); // 빈 메타정보를 담은 오브젝트
        helloDef.getPropertyValues().addPropertyValue("name", "Spring");  // 빈의 name 에 프로퍼티 주입
        System.out.println("[Singleton Cnt] before register hello2: " + ac.getBeanFactory().getSingletonCount());
        System.out.println("[BeanDefinition Cnt] before register hello2: " + ac.getBeanFactory().getBeanDefinitionCount());
        ac.registerBeanDefinition("hello2", helloDef); // 빈 등록
        System.out.println("[Singleton Cnt] after register hello2: " + ac.getBeanFactory().getSingletonCount());
        System.out.println("[BeanDefinition Cnt] after register hello2: " + ac.getBeanFactory().getBeanDefinitionCount());

        Hello hello2 = ac.getBean("hello2", Hello.class);
        System.out.println("[Singleton Cnt] get hello2: " + ac.getBeanFactory().getSingletonCount());
        System.out.println("[BeanDefinition Cnt] get hello2: " + ac.getBeanFactory().getBeanDefinitionCount());

        System.out.println(StringUtils.join(Arrays.asList(ac.getBeanFactory().getSingletonNames()), ","));
        System.out.println(StringUtils.join(Arrays.asList(ac.getBeanFactory().getBeanDefinitionNames()), ","));

    }

    @Test
    public void BEAN_DEFINITION_DI_테스트() {
        
    }

}
