package jun.spring.ch1.sub5;

import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.ChildBeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.beans.factory.support.ManagedSet;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class Ch151_4_PropertyPlaceholderBFPPTest {

    DefaultListableBeanFactory dlbf;

    @Before
    public void setUp() {
        dlbf = new DefaultListableBeanFactory();
    }

    @Test
    public void PPC_기초_적용_전_테스트() {
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("name", "${name}");
        RootBeanDefinition jbd1 = new RootBeanDefinition(Jun.class);
        jbd1.setPropertyValues(pvs);

        dlbf.registerBeanDefinition("jun1", jbd1);

        Jun jun = dlbf.getBean("jun1", Jun.class);
        assertEquals("${name}", jun.getName());
    }

    @Test
    public void PPC_기초_적용_테스트() {

        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("age", "${age}");
        pvs.add("name", "${name}");
        RootBeanDefinition jbd1 = new RootBeanDefinition(Jun.class);
        jbd1.setPropertyValues(pvs);

        dlbf.registerBeanDefinition("jun1", jbd1);

        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Properties props = new Properties();
        props.setProperty("age", "33");
        props.setProperty("name", "hyunjun");
        ppc.setProperties(props);
        ppc.postProcessBeanFactory(dlbf);

        Jun jun = dlbf.getBean("jun1", Jun.class);
        assertEquals(33, jun.getAge());
        assertEquals("hyunjun", jun.getName());
    }

    @Test
    public void PPC_빈_주입_적용_테스트() {
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("age", "${age}");
        pvs.add("name", "${name}");
        pvs.add("brother", new RuntimeBeanReference("${ref}"));
        RootBeanDefinition jbd1 = new RootBeanDefinition(Jun.class);
        jbd1.setPropertyValues(pvs);

        MutablePropertyValues pvs2 = new MutablePropertyValues();
        pvs2.add("age", 30);
        pvs2.add("name", "min");
        RootBeanDefinition jbd2 = new RootBeanDefinition(Jun.class);
        jbd2.setPropertyValues(pvs2);

        dlbf.registerBeanDefinition("jun1", jbd1);
        dlbf.registerBeanDefinition("jun2", jbd2);

        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Properties props = new Properties();
        props.setProperty("age", "33");
        props.setProperty("name", "hyunjun");
        props.setProperty("ref", "jun2");
        ppc.setProperties(props);
        ppc.postProcessBeanFactory(dlbf);

        Jun jun = dlbf.getBean("jun1", Jun.class);
        Jun jun2 = dlbf.getBean("jun2", Jun.class);
        assertEquals(33, jun.getAge());
        assertEquals("hyunjun", jun.getName());
        assertEquals(jun.getBrother()[0], jun2);
    }

    @Test
    public void PPC_조금_복잡한_주입_테스트() {
        Map singletonMap = Collections.singletonMap("myKey", "myValue");
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("age", "${age}");
        pvs.add("name", "${name}");
        pvs.add("brother", new RuntimeBeanReference("${ref}"));
        pvs.add("someMap", singletonMap);
        RootBeanDefinition jbd1 = new RootBeanDefinition(Jun.class);
        jbd1.setPropertyValues(pvs);

        MutablePropertyValues pvs2 = new MutablePropertyValues();
        pvs2.add("age", 30);
        pvs2.add("name", "min");
        RootBeanDefinition jbd2 = new RootBeanDefinition(Jun.class);
        jbd2.setPropertyValues(pvs2);

        dlbf.registerBeanDefinition("jun1", jbd1);
        dlbf.registerBeanDefinition("jun2", jbd2);

        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Properties props = new Properties();
        props.setProperty("age", "33");
        props.setProperty("name", "hyunjun");
        props.setProperty("ref", "jun2");
        ppc.setProperties(props);
        ppc.postProcessBeanFactory(dlbf);

        Jun jun = dlbf.getBean("jun1", Jun.class);
        Jun jun2 = dlbf.getBean("jun2", Jun.class);
        assertEquals(33, jun.getAge());
        assertEquals("hyunjun", jun.getName());
        assertEquals(jun.getBrother()[0], jun2);
    }

    @Test
    public void PPC_복잡한_주입_테스트() {
        Map singletonMap = Collections.singletonMap("myKey", "myValue");
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("age", "${age}");
        pvs.add("name", "name${var}${var}${");
        pvs.add("brother", new RuntimeBeanReference("${ref}"));
        pvs.add("someMap", singletonMap);
        RootBeanDefinition jbd1 = new RootBeanDefinition(Jun.class);
        jbd1.setPropertyValues(pvs);

        dlbf.registerBeanDefinition("jun1", jbd1);

        ConstructorArgumentValues cas = new ConstructorArgumentValues();
        cas.addIndexedArgumentValue(0, "${age}");
        cas.addGenericArgumentValue("${var}name${age}");

        MutablePropertyValues pvs1 = new MutablePropertyValues();

        pvs1.add("stringArray", new String[]{"${os.name}", "${age}"});

        List<Object> friends = new ManagedList<>();
        friends.add("na${age}me");
        friends.add(new RuntimeBeanReference("${ref}"));
        pvs1.add("friends", friends);

        Set<Object> someSet = new ManagedSet<>();
        someSet.add(new RuntimeBeanReference("${ref}"));
        someSet.add(new TypedStringValue("${age}", Integer.class));
        pvs1.add("someSet", someSet);

        Map<Object, Object> someMap = new ManagedMap<>();
        someMap.put(new TypedStringValue("key${age}"), new TypedStringValue("${age}"));
        someMap.put(new TypedStringValue("key${age}ref"), new RuntimeBeanReference("${ref}"));
        someMap.put("key1", new RuntimeBeanReference("${ref}"));
        someMap.put("key", "${age}name");

        MutablePropertyValues innerPvs = new MutablePropertyValues();
        innerPvs.add("touchy", "${os.name}");

        RootBeanDefinition innerBd = new RootBeanDefinition(Jun.class);
        innerBd.setPropertyValues(innerPvs);

        someMap.put("key3", innerBd);
        MutablePropertyValues innerPvs2 = new MutablePropertyValues(innerPvs);
        someMap.put("${key4}", new BeanDefinitionHolder(new ChildBeanDefinition("jun1", innerPvs2), "child"));

        pvs1.add("someMap", someMap);

        RootBeanDefinition jbd2 = new RootBeanDefinition(Jun.class, cas, pvs1);
        dlbf.registerBeanDefinition("jun2", jbd2);

        RootBeanDefinition jbd3 = new RootBeanDefinition(Jun.class);
        dlbf.registerBeanDefinition("jun3", jbd3);

        PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
        Properties props = new Properties();
        props.setProperty("age", "33");
        props.setProperty("var", "${m}var");
        props.setProperty("ref", "jun3");
        props.setProperty("m", "my");
        props.setProperty("key4", "myKey4");
        props.setProperty("parent", "parent1");

        ppc.setProperties(props);
        ppc.postProcessBeanFactory(dlbf); // 실행

        Jun jun1 = (Jun) dlbf.getBean("jun1");
        Jun jun2 = (Jun) dlbf.getBean("jun2");

        assertEquals(jun1.getAge(), 33);
        System.out.println(jun1);
    }

    static interface Person {

    }

    @Data
    static class Jun implements Person {

        public Jun() {};

        public Jun(int age, String name) {
            this.age = age;
            this.name = name;
        }

        private String name;
        private int age;
        private Person[] brother;
        private String touchy;
        private String[] stringArray;
        private Collection<? super Object> friends = new LinkedList<>();
        private Map<?, ?> someMap = new HashMap<>();
        private Set<?> someSet = new HashSet<>();
    }

}
