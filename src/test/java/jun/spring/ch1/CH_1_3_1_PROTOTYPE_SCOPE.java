package jun.spring.ch1;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CH_1_3_1_PROTOTYPE_SCOPE {

    @Test
    public void 싱글톤_SCOPE_테스트() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class, SingletonClientBean.class);
        Set<SingletonBean> beans = new HashSet<>();

        beans.add(ac.getBean(SingletonBean.class));
        beans.add(ac.getBean(SingletonBean.class));
        assertEquals(beans.size(), 1);

        beans.add(ac.getBean(SingletonClientBean.class).bean1);
        beans.add(ac.getBean(SingletonClientBean.class).bean2);

        assertEquals(beans.size(), 1);
    }

    @Test
    public void 프로토타입_SCOPE_테스트() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, PrototypeClientBean.class);
        Set<PrototypeBean> bean = new HashSet<>();

        bean.add(ac.getBean(PrototypeBean.class));
        assertEquals(bean.size(), 1);
        bean.add(ac.getBean(PrototypeBean.class));
        assertEquals(bean.size(), 2);

        bean.add(ac.getBean(PrototypeClientBean.class).bean1);
        assertEquals(bean.size(), 3);
        bean.add(ac.getBean(PrototypeClientBean.class).bean2);
        assertEquals(bean.size(), 4);
    }

    static class SingletonBean {}
    static class SingletonClientBean {

        @Autowired
        SingletonBean bean1;

        @Autowired
        SingletonBean bean2;
    }

    @Scope("prototype")
    static class PrototypeBean {}
    static class PrototypeClientBean {
        @Autowired
        PrototypeBean bean1;
        @Autowired
        PrototypeBean bean2;
    }

}
