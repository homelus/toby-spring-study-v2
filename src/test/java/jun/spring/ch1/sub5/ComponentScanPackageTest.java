package jun.spring.ch1.sub5;

import jun.spring.ch1.component.MarkerComponent;
import jun.spring.ch1.component.child.ChildComponent;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentScanPackageTest {

    @Test
    public void 컴포넌트_스캔_패키지_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PackageConfig.class);
        assertNotNull(context.getBean("myComponent"));
    }

    @Test
    public void 인터페이스_스캔_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InterfaceConfig.class);
        assertNotNull(context.getBean("myComponent"));
        assertNotNull(context.getBean("childComponent"));
        assertFalse(context.containsBeanDefinition("myMarkingComponent"));
    }

    @Test
    public void 컴포넌트_패키지_스캔_제외_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExcludePackage.class);
        assertFalse(context.containsBeanDefinition("childComponent"));
        assertNotNull(context.getBean("myComponent"));
    }

    @Configuration
    @ComponentScan(basePackages = "jun.spring.ch1.component")
    static class PackageConfig {}

    /**
     * 인터페이스가 존재하는 패키지가 Base 패키지가 된다.
     */
    @Configuration
    @ComponentScan(basePackageClasses = MarkerComponent.class)
    static class InterfaceConfig {}

    @Configuration
    @ComponentScan(basePackages = "jun.spring.ch1.component",
        excludeFilters = @Filter(type= FilterType.ASSIGNABLE_TYPE, value=ChildComponent.class))
    static class ExcludePackage {}

}
