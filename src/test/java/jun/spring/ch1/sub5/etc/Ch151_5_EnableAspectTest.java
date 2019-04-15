package jun.spring.ch1.sub5.etc;

import jun.spring.ch1.enable.aspect.ITestService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import static org.junit.Assert.assertTrue;

public class Ch151_5_EnableAspectTest {

    @Test
    public void ASPECT_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AspectJConfig.class);
        ITestService testService = context.getBean(ITestService.class);
        testService.test();

        System.out.println(testService.getClass().getName());
        assertTrue(testService.getClass().getName().contains("CGLIB"));
        System.out.println("main completed");
    }

    @Test
    public void ASPECTJ_PROXY_TARGET_TRUE_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AspectJ_JdkProxyConfig.class);
        ITestService testService = context.getBean(ITestService.class);
        testService.test();

        System.out.println(testService.getClass().getName());
        assertTrue(testService.getClass().getName().contains("$Proxy"));
        System.out.println("main completed");
    }

    /**
     * proxyTargetClass 가 true 인 경우 CGLIB Proxy 가 생성
     */
    @Configuration
    @ComponentScan("jun.spring.ch1.enable.aspect")
    @EnableAspectJAutoProxy(proxyTargetClass = true)
    static class AspectJConfig {}

    /**
     * proxyTargetClass 가 Defulat false 인 경우 JDKDynamicProxy 가 생성
     */
    @Configuration
    @ComponentScan("jun.spring.ch1.enable.aspect")
    @EnableAspectJAutoProxy
    static class AspectJ_JdkProxyConfig {}

}
