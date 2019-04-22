package jun.spring.ch1.sub5;

import jun.spring.ch1.profile.eachPkg.EachMainConfig;
import jun.spring.ch1.profile.importPkg.ImportMainConfig;
import jun.spring.ch1.profile.nestedPkg.NestedMainConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import java.util.Arrays;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProfileTest {

    @Test
    public void 프로파일_XML_테스트() {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.getEnvironment().setActiveProfiles("dev");
        context.load(new ClassPathResource("profile-test.xml"));
        context.refresh();

        assertFalse(contains(context.getBeanDefinitionNames(), "realBean"));
        assertFalse(contains(context.getBeanDefinitionNames(), "qaBean"));
        assertTrue(contains(context.getBeanDefinitionNames(), "devBean"));
        assertFalse(contains(context.getBeanDefinitionNames(), "publicBean"));
    }

    @Test
    public void 프로파일_JAVA_EACH_테스트() {
//        System.getProperties().put("spring.profiles.active", "dev");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(EachMainConfig.class); // 각각 클래스
        assertFalse(contains(context.getBeanDefinitionNames(), "realBean"));
        assertFalse(contains(context.getBeanDefinitionNames(), "qaBean"));
        assertFalse(contains(context.getBeanDefinitionNames(), "devBean"));
        assertFalse(contains(context.getBeanDefinitionNames(), "publicBean"));

        System.out.println(String.join(",", context.getBeanDefinitionNames()));
    }

    @Test
    public void 프로파일_JAVA_IMPORT_테스트() {
        System.getProperties().put("spring.profiles.active", "qa");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ImportMainConfig.class); // Import 클래스
        assertFalse(contains(context.getBeanDefinitionNames(), "devBean"));
        assertTrue(contains(context.getBeanDefinitionNames(), "qaBean"));
        assertFalse(contains(context.getBeanDefinitionNames(), "realBean"));
        assertTrue(contains(context.getBeanDefinitionNames(), "publicBean"));

        System.out.println(String.join(",", context.getBeanDefinitionNames()));
    }

    @Test
    public void 프로파일_JAVA_NESTED_테스트() {
        System.getProperties().put("spring.profiles.active", "real");
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(NestedMainConfig.class); // 중첩 클래스
        assertFalse(contains(context.getBeanDefinitionNames(), "qaBean"));
        assertFalse(contains(context.getBeanDefinitionNames(), "devBean"));
        assertTrue(contains(context.getBeanDefinitionNames(), "realBean"));
        assertTrue(contains(context.getBeanDefinitionNames(), "publicBean"));

        System.out.println(String.join(",", context.getBeanDefinitionNames()));
    }

    private boolean contains(String[] bds, String name) {
        return Arrays.asList(bds).contains(name);
    }

}
