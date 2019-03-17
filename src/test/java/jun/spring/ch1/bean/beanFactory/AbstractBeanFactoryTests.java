package jun.spring.ch1.bean.beanFactory;

import jun.spring.ch1.model.TestBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import static org.junit.Assert.*;

public abstract class AbstractBeanFactoryTests {

    protected abstract BeanFactory getBeanFactory();

    @Test
    public void inheritance() {
        assertTrue(getBeanFactory().containsBean("rod"));
        assertTrue(getBeanFactory().containsBean("roderick"));
        TestBean rod = (TestBean) getBeanFactory().getBean("rod");
        TestBean roderick = (TestBean) getBeanFactory().getBean("roderick");
        assertNotSame("not == ", rod, roderick);
        assertEquals("rod.name id Rod", "Rod", rod.getName());
        assertEquals("rod.age is 31", 31, rod.getAge());
        assertEquals("roderick.name is Roderick", "Roderick", roderick.getName());
        assertEquals("roderick.age was inherited", roderick.getAge(), rod.getAge());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBeanWithNullArg() {
        getBeanFactory().getBean((String) null);
    }



}
