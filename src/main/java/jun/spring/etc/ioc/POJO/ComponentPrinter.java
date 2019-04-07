package jun.spring.etc.ioc.POJO;

import org.springframework.stereotype.Component;

@Component
public class ComponentPrinter implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
