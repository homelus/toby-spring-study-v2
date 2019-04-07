package jun.spring.etc.ioc.POJO;

import org.springframework.stereotype.Component;

@Component
public class ManyPrinter2 implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
