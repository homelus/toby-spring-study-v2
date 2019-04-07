package jun.spring.ch1.sub5;

import org.junit.Test;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class CH_1_5_1_BEAN_ROLE {

    @Test
    public void 빈의_역할_구분_기초테스트() {
        MockServletContext sc = new MockServletContext();
        XmlWebApplicationContext ac = new XmlWebApplicationContext();
        ac.setServletContext(sc);
        ac.setConfigLocation("ch1_5/spring-config.xml");
        ac.refresh();
        System.out.println(String.join(",", ac.getBeanDefinitionNames()));
    }


}
