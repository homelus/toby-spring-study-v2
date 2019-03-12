package jun.spring.ch1.bean.beanFactory;

import jun.spring.ch1.model.TestBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import static org.junit.Assert.assertTrue;

public abstract class AbstractBeanFactoryTests {

    protected abstract BeanFactory getBeanFactory();

    @Test
    public void inheritance() {
        assertTrue(getBeanFactory().containsBean("rod"));
        assertTrue(getBeanFactory().containsBean("roderick"));
        TestBean rod = (TestBean) getBeanFactory().getBean("rod");
        TestBean roderick = (TestBean) getBeanFactory().getBean("roderick");
        assertTrue("not == ", rod != roderick);
        assertTrue("rod.name id Rod", rod.getName().equals("Rod"));
        assertTrue("rod.age is 31", rod.getAge() == 31);
        assertTrue("roderick.name is Roderick", roderick.getName().equals("Roderick"));
        assertTrue("roderick.age was inherited", roderick.getAge() == rod.getAge());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getBeanWithNullArg() {
        getBeanFactory().getBean((String) null);
    }



}
