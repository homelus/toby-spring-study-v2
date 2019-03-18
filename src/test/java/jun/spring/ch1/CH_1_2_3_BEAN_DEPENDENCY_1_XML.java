package jun.spring.ch1;

import jun.spring.ch1.ioc.POJO.ComponentFieldComputer;
import jun.spring.ch1.ioc.POJO.ComponentMehtodComputer;
import jun.spring.ch1.ioc.POJO.Computer;
import jun.spring.ch1.ioc.POJO.NotNameMatchingComputer;
import jun.spring.ch1.ioc.POJO.ResourceComputer;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CH_1_2_3_BEAN_DEPENDENCY_1_XML {

    @Test
    public void XML_수정자_생성자_빈_주입_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/dependency.xml");
        Computer computerProperty = ac.getBean("computerProperty", Computer.class);
        Computer computerConstructor = ac.getBean("computerConstructor", Computer.class);
        Computer computerConstructorType = ac.getBean("computerConstructorType", Computer.class);

        assertThat(computerProperty, is(notNullValue()));
        assertThat(computerConstructor, is(notNullValue()));
        assertThat(computerConstructorType, is(notNullValue()));

        assertEquals(computerProperty.getName(), "Property");
        assertEquals(computerConstructor.getName(), "Constructor");
        assertEquals(computerConstructorType.getName(), "TypeConstructor");

        assertThat(computerProperty.getPrinter(), is(notNullValue()));
        assertThat(computerConstructor.getPrinter(), is(notNullValue()));
        assertThat(computerConstructorType.getPrinter(), is(notNullValue()));
    }

    @Test
    public void XML_빈_이름_자동와이어링_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/autowireNameType.xml");
        Computer 오토와이어링_안된_컴퓨터 = ac.getBean("computerNone", Computer.class);
        Computer 이름에의한_오토와이어링_컴퓨터 = ac.getBean("computerByName", Computer.class);
        NotNameMatchingComputer 타입에의한_오토와이어링_컴퓨터 = ac.getBean("computerByType", NotNameMatchingComputer.class);

        assertThat(오토와이어링_안된_컴퓨터.getPrinter(), is(nullValue()));
        assertThat(이름에의한_오토와이어링_컴퓨터.getPrinter(), is(notNullValue()));
        assertThat(타입에의한_오토와이어링_컴퓨터.getMainPrinter(), is(notNullValue()));
    }

    @Test
    public void 애노테이션_RESOURCE_ANNOTATION_CONFIG_수정자_테스트() {
        /**
         * annotation-config 설정 후 테스트 필요
         */
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/resourceDI.xml");
        Computer RESOURCE_설정되지_않은_컴퓨터 = ac.getBean("nonResourceComputer", Computer.class);
        ResourceComputer RESOURCE_설정된_컴퓨터 = ac.getBean("resourceComputer", ResourceComputer.class);

        assertThat(RESOURCE_설정되지_않은_컴퓨터.getPrinter(), is(nullValue()));
        assertThat(RESOURCE_설정된_컴퓨터.getPrinter(), is(notNullValue()));
    }

    @Test
    public void 애노테이션_RESOURCE_COMPONENT_SCAN_수정자_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/componentScanDI.xml");
        ComponentMehtodComputer computer = ac.getBean("componentMehtodComputer", ComponentMehtodComputer.class);

        assertThat(computer, is(notNullValue()));
        assertThat(computer.getComponentPrinter(), is(notNullValue()));
        assertThat(computer.getMainPrinter(), is(nullValue())); // 이상함.
    }

    @Test(expected = BeanCreationException.class)
    public void 애노테이션_RESOURCE_COMPONENT_SCAN_필드_테스트() {
        /**
         * 일치하는 resource 가 없는 경우 예외 발생
         * resource name 설정하고 테스트 필요
         */
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/componentScanDI.xml");
        ComponentFieldComputer computer = ac.getBean("componentFieldComputer", ComponentFieldComputer.class);

        assertThat(computer, is(notNullValue()));
        assertThat(computer.getComponentPrinter(), is(notNullValue()));
        assertThat(computer.getMainPrinter(), is(nullValue()));
    }

}
