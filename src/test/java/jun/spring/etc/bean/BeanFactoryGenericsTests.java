package jun.spring.etc.bean;

import jun.spring.etc.model.GenericBean;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;

public class BeanFactoryGenericsTests {

    @Test
    public void GENERIC_SET_PROPERTY_테스트() {
        DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
        RootBeanDefinition rbd = new RootBeanDefinition(GenericBean.class);

        Set<String> input = new HashSet<>();
        input.add("4");
        input.add("5");
        rbd.getPropertyValues().add("integerSet", input);
        bf.registerBeanDefinition("genericBean", rbd);
        GenericBean<?> gb = (GenericBean<?>) bf.getBean("genericBean");

        assertTrue(gb.getIntegerSet().contains(new Integer(4)));
        assertTrue(gb.getIntegerSet().contains(new Integer(5)));
    }

}
