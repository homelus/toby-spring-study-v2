package jun.spring.ch1.sub5;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class ConfirmBeanUtilsTest {

    @Test
    public void ANNOTATION_CONFIG_속해있는_ROLE_빈이름_출력_테스트() {
        GenericApplicationContext context = new GenericXmlApplicationContext("beanrole-with-componentScan.xml");
        List<List<String>> roleBeanInfos = new ArrayList<>();
        roleBeanInfos.add(new ArrayList<String>());
        roleBeanInfos.add(new ArrayList<String>());
        roleBeanInfos.add(new ArrayList<String>());

        for (String name : context.getBeanDefinitionNames()) {
            int role = context.getBeanDefinition(name).getRole();
            List<String> beanInfos = roleBeanInfos.get(role);
            beanInfos.add(role + "\t" + name + "\t" + context.getBean(name).getClass().getName());
        }

        for (List<String> roleBeanInfo : roleBeanInfos) {
            for (String beanInfo : roleBeanInfo) {
                System.out.println(beanInfo);
            }
        }

    }

    @Test
    public void ROLE_설정_테스트() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        List<List<String>> roleBeanInfos = new ArrayList<>();
        roleBeanInfos.add(new ArrayList<String>());
        roleBeanInfos.add(new ArrayList<String>());
        roleBeanInfos.add(new ArrayList<String>());

        for (String name : context.getBeanDefinitionNames()) {
            int role = context.getBeanDefinition(name).getRole();
            List<String> beanInfos = roleBeanInfos.get(role);
            beanInfos.add(role + "\t" + name + "\t" + context.getBean(name).getClass().getName());
        }

        for (List<String> roleBeanInfo : roleBeanInfos) {
            for (String beanInfo : roleBeanInfo) {
                if (beanInfo.contains("CustomBean")) {
                    System.out.println(beanInfo);
                }
            }
        }
    }


    @Configuration
    static class Config {

        @Bean
        @Role(0)
        public CustomBean applicationBean() {
            return new CustomBean();
        }

        @Bean()
        @Role(2)
        CustomBean containerBean() {
            return new CustomBean();
        }

    }

    static class CustomBean {

    }



}
