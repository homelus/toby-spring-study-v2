package jun.spring.ch1;

import jun.spring.ch1.ioc.POJO.ComponentAutowiredEtcComputer;
import jun.spring.ch1.ioc.POJO.ComponentAutowiredQualifierComputer;
import jun.spring.ch1.ioc.POJO.ConsolePrinter;
import jun.spring.ch1.ioc.POJO.ManyPrinter1;
import jun.spring.ch1.ioc.POJO.Printer;
import org.junit.Test;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Collection;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CH_1_2_3_BEAN_DEPENDENCY_3_ETC {

    @Test(expected = BeanCreationException.class)
    public void AUTOWIRED_동일한_타입_2개_실패_테스트() {
        /**
         * No unique bean of type [jun.spring.ch1.ioc.POJO.Printer] is defined:
         * expected single matching bean but found 4: [printer1, printer2, printer3, printer4]
         */
        new GenericXmlApplicationContext("/dependency/failDependency.xml");
        fail();
    }

    @Test
    public void 컬렉션_배열_맵_AUTOWIRED_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/manyDependency.xml");
        ComponentAutowiredEtcComputer computer = ac.getBean("computer", ComponentAutowiredEtcComputer.class);

        Printer[] printerArray = computer.getPrinterArray();
        Collection<Printer> printerCollection = computer.getPrinterCollection();
        Map<String, Printer> printerMap = computer.getPrinterMap();

        assertEquals(printerArray.length, 4);
        assertEquals(printerCollection.size(), 4);
        assertEquals(printerMap.size(), 4);

        assertEquals(printerArray[0].getClass(), ConsolePrinter.class);
        assertEquals(printerCollection.iterator().next().getClass(), ConsolePrinter.class);
        assertEquals(printerMap.get("printer1").getClass(), ConsolePrinter.class);
    }

    @Test
    public void QUALIFIER_테스트() {
        GenericXmlApplicationContext ac = new GenericXmlApplicationContext("/dependency/qualifierDependency.xml");
        ComponentAutowiredQualifierComputer computer = ac.getBean("computer", ComponentAutowiredQualifierComputer.class);

        assertThat(computer.getPrinter(), is(notNullValue()));
        assertEquals(computer.getPrinter().getClass(), ManyPrinter1.class);
    }

}
