package jun.spring.ch1.config;

import jun.spring.ch1.ioc.POJO.Hello;
import jun.spring.ch1.ioc.POJO.Printer;
import jun.spring.ch1.ioc.POJO.StringPrinter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HelloConfig {

    @Bean
    public Hello hello() {
        Hello hello = new Hello();
        hello.setName("Spring");
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Hello hello2() {
        Hello hello = new Hello();
        hello.setName("Spring2");
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Printer printer() {
        return new StringPrinter(); // 싱글톤이므로 동일한 인스턴스를 반환한다.
    }
}
