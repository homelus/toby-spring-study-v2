package jun.spring.ch1.sub2;

import com.sun.deploy.util.StringUtils;
import jun.spring.etc.ioc.POJO.Hello;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

public class CH_1_2_1_BEAN_DEFINITION {

    @Test
    public void 빈_DEFINITION_기초_테스트() {
        // TODO
    }

    @Test
    public void 빈_DEFINITION_기본테스트() {
        BeanDefinition testBean = new RootBeanDefinition(TestBean.class);
        assertEquals(testBean.getBeanClassName(), "jun.spring.ch1.sub2.CH_1_2_1_BEAN_DEFINITION$TestBean");
        assertTrue(testBean.isSingleton());
    }

    @Test
    public void 빈_DEFINITION_DI_테스트() {
        StaticApplicationContext ac = new StaticApplicationContext();

        BeanDefinition dependent = new RootBeanDefinition(DependentBean.class);
        BeanDefinition test = new RootBeanDefinition(TestBean.class);
        test.getPropertyValues().addPropertyValue("name", "Spring");
        test.getPropertyValues().addPropertyValue("dependent", new RuntimeBeanReference("dependent"));

        ac.registerBeanDefinition("dependent", dependent); // 종속 빈 주입
        ac.registerBeanDefinition("test", test);

        TestBean testBean = ac.getBean("test", TestBean.class);
        assertEquals(testBean.getName(), "Spring");
        assertThat(testBean.getDependent(), is(notNullValue()));
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
        assertThat(hello1.sayHello(), CoreMatchers.is("Hello null"));
        assertThat(hello1, CoreMatchers.is(CoreMatchers.notNullValue()));
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

    private static class TestBean {
        private String name;
        private DependentBean dependent;

        public String getName() {
            return name;
        }

        public DependentBean getDependent() {
            return dependent;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setDependent(DependentBean dependent) {
            this.dependent = dependent;
        }
    }

    private static class DependentBean {

    }

}
