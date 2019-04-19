package jun.spring.ch1.sub5.etc;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import javax.annotation.PostConstruct;
import java.util.Properties;

import static junit.framework.TestCase.assertEquals;

public class Ch151_9_PropertyTests {

    @Test
    public void 환경변수_시스템_프로퍼티_테스트() {
        System.out.println("--------System Environment--------");
        System.getenv().forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("----------------------------------");

        System.out.println("--------System Properties--------");
        System.getProperties().forEach((k, v) -> System.out.println(k + " : " + v));
        System.out.println("----------------------------------");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();
        StandardEnvironment standardEnvironment = context.getBean(StandardEnvironment.class);
        System.out.println(standardEnvironment.getPropertySources().toString());
        System.out.println("----------------------------------");
        standardEnvironment.getSystemProperties().forEach((k, v) -> System.out.println(k + " : " + v));
    }

    @Test
    public void 프토퍼티_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.refresh();

        System.out.println(context.getEnvironment().getProperty("os.name")); // 시스템 프로퍼티
        System.out.println(context.getEnvironment().getProperty("Path")); // 환경 변수

        Properties p = new Properties();
        p.put("db.username", "spring");
        PropertySource<?> ps = new PropertiesPropertySource("customPropertySource", p);

        Properties p2 = new Properties();
        p.put("db.url", "www");
        PropertySource<?> ps2 = new PropertiesPropertySource("customPropertySource", p);

        /**
         * 프로퍼티를 환경 오브젝트에 직접 추가하는 경우 우선순위를 함께 지정한다.
         * addFirst 는 현재 등록된 프로퍼티 소스보다 우선순위가 높게 지정된다.
         * addLst 는 가장 낮은 우선 순위를 갖는다.
         */
        context.getEnvironment().getPropertySources().addFirst(ps);
        context.getEnvironment().getPropertySources().addLast(ps2);

        assertEquals("spring", context.getEnvironment().getProperty("db.username"));
        assertEquals("www", context.getEnvironment().getProperty("db.url"));

    }

    @Test
    public void 프로퍼티_환경_조회_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertyConfig.class);
        PropertyClass propertyClass = context.getBean("propertyClass", PropertyClass.class);
        System.out.println(propertyClass.osName);
        System.out.println(propertyClass.path);
    }

    @Configuration
    static class PropertyConfig {

        @Autowired
        Environment env;

        @Bean
        public PropertyClass propertyClass() {
            PropertyClass propertyClass = new PropertyClass();
            propertyClass.path = env.getProperty("Path");
            return propertyClass;
        }

    }

    static class PropertyClass {
        @Autowired
        Environment environment;

        private String osName;

        private String path;

        @PostConstruct
        public void init() {
            this.osName = environment.getProperty("os.name");
        }


    }

}
