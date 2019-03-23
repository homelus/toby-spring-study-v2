package jun.spring.ch1;

import jun.spring.ch1.ioc.POJO.MainDeal;
import jun.spring.ch1.ioc.POJO.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CH_1_2_4_PROPERTY {

    @Test
    public void XML_프로퍼티_값_종류_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/property/property.xml");
        MainDeal mainDeal = ac.getBean("mainDeal", MainDeal.class);
        assertThat(mainDeal.getDealNo(), is(1L));
        assertThat(mainDeal.getTitle(), is("컴퓨터"));
        assertEquals(mainDeal.getClazz(), String.class);
    }

    @Test
    public void ANNOTAITON_프로퍼티_값_테스트() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TDeal.class);
        TDeal deal = ac.getBean(TDeal.class);
        assertEquals(deal.getName(), "Everyone");
    }

    @Test
    public void 외부값_참조_프로퍼티_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/property/ref-property.xml");
        User user = ac.getBean("user", User.class);

        assertThat(user.getOsName(), is(notNullValue()));
        assertEquals(user.getPassword(), "asdf111");
        assertEquals(user.getUsername(), "Jun");
    }


    private static class TDeal {
        private String name;

        public String getName() {
            return name;
        }

        @Value("Everyone")
        public void setName(String name) {
            this.name = name;
        }

    }

}
