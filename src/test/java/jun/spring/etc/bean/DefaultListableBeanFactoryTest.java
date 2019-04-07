package jun.spring.etc.bean;

import jun.spring.etc.model.ITestBean;
import jun.spring.etc.model.TestBean;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class DefaultListableBeanFactoryTest {

    @Test
    public void 빈_등록_테스트() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        TestBean tb = lbf.createBean(TestBean.class);
        assertSame(lbf, tb.getBeanFactory());
    }

    @Test
    public void DEFAULT_초기화_스프링_빈() {
        KnowsIfInstantiated.clearInstantiationRecord();
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        RootBeanDefinition rbd = new RootBeanDefinition(KnowsIfInstantiated.class.getName());
        assertFalse("싱글톤 인스턴스화 전", KnowsIfInstantiated.wasInstantiated());
        lbf.registerBeanDefinition("test", rbd);
        assertFalse("싱글톤 인스턴스화 전", KnowsIfInstantiated.wasInstantiated());
        lbf.preInstantiateSingletons();
        assertTrue("싱글톤 인스턴스화 전", KnowsIfInstantiated.wasInstantiated());
        lbf.getBean("test");
        assertTrue("싱글톤 인스턴스화 전", KnowsIfInstantiated.wasInstantiated());
    }

    @Test
    public void LAZY_초기화_스프링_빈() {
        KnowsIfInstantiated.clearInstantiationRecord();
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        RootBeanDefinition rbd = new RootBeanDefinition(KnowsIfInstantiated.class.getName());
        rbd.setLazyInit(true);
        assertFalse("싱글톤 인스턴스화 전", KnowsIfInstantiated.wasInstantiated());
        lbf.registerBeanDefinition("test", rbd);
        assertFalse("싱글톤 인스턴스화 전", KnowsIfInstantiated.wasInstantiated());
        lbf.preInstantiateSingletons();
        assertFalse("싱글톤 인스턴스화 전", KnowsIfInstantiated.wasInstantiated());
        lbf.getBean("test");
        assertTrue("싱글톤 인스턴스화 전", KnowsIfInstantiated.wasInstantiated());
    }

    @Test
    public void 인터페이스_빈_등록() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        lbf.registerBeanDefinition("test", new RootBeanDefinition((ITestBean.class)));
        try {
            lbf.getBean("test");
            fail("Should have thrown BeanCreationException");
        } catch (BeanCreationException ex) {
            assertEquals("test", ex.getBeanName());
            assertTrue(ex.getMessage().toLowerCase().contains("interface"));
        }
    }

    @Test
    public void 추상화_클래스_빈_등록() {
        DefaultListableBeanFactory lbf = new DefaultListableBeanFactory();
        lbf.registerBeanDefinition("test", new RootBeanDefinition(AbstractBeanFactory.class));
        try {
            lbf.getBean("test");
            fail("Should have thrown BeanCreateionException");
        } catch (BeanCreationException ex) {
            assertEquals("test", ex.getBeanName());
            assertTrue(ex.getMessage().toLowerCase().contains("abstract"));
        }
    }

    private static class KnowsIfInstantiated {

        private static boolean instantiated;

        public static void clearInstantiationRecord() {
            instantiated = false;
        }

        public static boolean wasInstantiated() {
            return instantiated;
        }

        public KnowsIfInstantiated() {
            instantiated = true;
        }
    }

}
