package jun.spring.ch1.ioc.POJO;

import jun.spring.ch1.annotation.SubPrinter;
import org.springframework.stereotype.Component;

@Component
public class ManyPrinter2 implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
