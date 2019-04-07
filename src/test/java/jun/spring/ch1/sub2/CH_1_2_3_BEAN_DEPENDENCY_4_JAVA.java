package jun.spring.ch1.sub2;

import jun.spring.etc.ioc.POJO.ConsolePrinter;
import jun.spring.etc.ioc.POJO.Printer;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;

public class CH_1_2_3_BEAN_DEPENDENCY_4_JAVA {

    @Test
    public void 애노테이션_AUTOWIRED_설정_테스트() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Config.class);
        Device device = ac.getBean("device", Device.class);

        assertThat(device, is(notNullValue()));
    }

    @Test
    public void 애노테이션_메소드_설정_테스트() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MethodConfig.class);
        NonAutowiredDevice device = ac.getBean("device", NonAutowiredDevice.class);
        NonAutowiredDevice anotherDevice = ac.getBean("anotherDevice", NonAutowiredDevice.class);
        assertThat(device, is(notNullValue()));
        assertThat(anotherDevice, is(notNullValue()));
        assertThat(anotherDevice.getPrinter(), is(sameInstance(device.getPrinter())));
    }

    @Test
    public void 애노테이션_메소드_와이어링_테스트() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(MethodWireConfig.class);
        NonAutowiredDevice device = ac.getBean("device", NonAutowiredDevice.class);

        assertThat(device, is(notNullValue()));
        assertThat(device.getPrinter(), is(notNullValue()));
    }

}

@Configuration
class MethodWireConfig {
    @Bean
    public NonAutowiredDevice device(Printer printer) {
        NonAutowiredDevice device = new NonAutowiredDevice();
        device.setPrinter(printer);
        return device;
    }

    @Bean
    public Printer printer() {
        return new ConsolePrinter();
    }
}

@Configuration
class MethodConfig {
    @Bean
    public NonAutowiredDevice device() {
        NonAutowiredDevice device = new NonAutowiredDevice();
        device.setPrinter(printer());
        return device;
    }

    @Bean
    public NonAutowiredDevice anotherDevice() {
        NonAutowiredDevice device = new NonAutowiredDevice();
        device.setPrinter(printer());
        return device;
    }

    @Bean
    public Printer printer() {
        return new ConsolePrinter();
    }
}

@Configuration
class Config {

    @Bean
    public Device device() {
        return new Device();
    }

    @Bean
    public Printer printer() {
        return new ConsolePrinter();
    }

}

@Data
class NonAutowiredDevice {
    Printer printer;
}

@Data
class Device {
    @Autowired
    Printer printer;
}
