package jun.spring.ch1.sub2;

import jun.spring.etc.ioc.POJO.ComponentAutowiredComputer;
import jun.spring.etc.ioc.POJO.ComponentConstructAutowiredComputer;
import jun.spring.etc.ioc.POJO.ComponentMethodAutowiredComputer;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class CH_1_2_3_BEAN_DEPENDENCY_2_ANNOTATION {

    @Test
    public void 애노테이션_AUTOWIRED_필드_수정자_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/componentScanDI.xml");
        ComponentAutowiredComputer computer = ac.getBean("autowiredComputer", ComponentAutowiredComputer.class);

        assertThat(computer.getFieldAutoiwredPrinter(), is(notNullValue()));
        assertThat(computer.getMethodAutoiwredPrinter(), is(notNullValue()));
    }

    @Test
    public void 애노테이션_AUTOWIRED_생성자_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/componentScanDI.xml");
        ComponentConstructAutowiredComputer computer = ac.getBean("componentConstructAutowiredComputer", ComponentConstructAutowiredComputer.class);

        assertThat(computer.getPrinter(), is(notNullValue()));
    }

    @Test
    public void 애노테이션_AUTOWIRED_일반메소드_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/componentScanDI.xml");
        ComponentMethodAutowiredComputer computer = ac.getBean("componentMethodAutowiredComputer", ComponentMethodAutowiredComputer.class);

        assertThat(computer.getPrinter(), is(notNullValue()));
    }

}
