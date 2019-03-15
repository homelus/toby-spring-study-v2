package jun.spring.ch1.service;

import jun.spring.ch1.ioc.POJO.Hello;
import jun.spring.ch1.ioc.POJO.Printer;
import jun.spring.ch1.ioc.POJO.StringPrinter;
import org.springframework.context.annotation.Bean;

public class HelloService_1 {

    @Bean
    public Hello hello1() {
        Hello hello = new Hello();
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Hello hello2() {
        Hello hello = new Hello();
        hello.setPrinter(printer());
        return hello;
    }

    @Bean
    public Printer printer() {
        return new StringPrinter();
    }

}
