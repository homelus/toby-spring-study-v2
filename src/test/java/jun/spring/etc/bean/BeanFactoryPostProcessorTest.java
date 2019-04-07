package jun.spring.etc.bean;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.GenericApplicationContext;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class BeanFactoryPostProcessorTest {

    @Test
    public void BFPP_TEST() {
        GenericApplicationContext ac = new GenericApplicationContext();
        ac.addBeanFactoryPostProcessor(new TestBeanFactoryPostProcessor());

        ac.registerBeanDefinition("bf", new RootBeanDefinition(TestBeanFactoryPostProcessor.class));
        ac.registerBeanDefinition("test", new RootBeanDefinition(TestBean.class));
        TestBean testBean = ac.getBean("test", TestBean.class);

        assertThat(testBean, is(notNullValue()));
        testBean.print();

    }

    private static class TestBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            System.out.println("BeanPostProcessor");
        }
    }

    private static class TestBean {
        public void print() {
            System.out.println("print");
        }
    }

}
