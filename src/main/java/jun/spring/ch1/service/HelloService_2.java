package jun.spring.ch1.service;

import jun.spring.ch1.ioc.POJO.Hello;
import jun.spring.ch1.ioc.POJO.Printer;
import jun.spring.ch1.ioc.POJO.StringPrinter;
import org.springframework.context.annotation.Bean;

public class HelloService_2 {

    private Printer printer;

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    @Bean
    public Hello hello1() {
        Hello hello = new Hello();
        hello.setPrinter(this.printer);
        return hello;
    }

    @Bean
    public Hello hello2() {
        Hello hello = new Hello();
        hello.setPrinter(this.printer);
        return hello;
    }

    @Bean
    public Printer printer() {
        return new StringPrinter();
    }

}
