package jun.spring.ch1.ioc.POJO;

import lombok.Data;

@Data
public class Computer {

    public Computer() {}

    public Computer(String name, Printer printer) {
        this.name = name;
        this.printer = printer;
    }

    String name;
    Printer printer;
}
