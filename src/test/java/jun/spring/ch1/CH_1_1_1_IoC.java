package jun.spring.ch1;

import org.junit.Test;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.support.GenericApplicationContext;

public class CH_1_1_1_IoC {

    @Test
    public void POJO클래스와_설정메타정보를_가지고_애플리케이션컨텍스트_만들기() {
        GenericApplicationContext ac = new GenericApplicationContext();
        AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(ac);

    }

    private static class POJO_CLASS {
        private String name;
    }

}
