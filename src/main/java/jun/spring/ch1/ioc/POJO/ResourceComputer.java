package jun.spring.ch1.ioc.POJO;

import javax.annotation.Resource;

public class ResourceComputer {

    private String name;

    private Printer printer;

    @Resource
    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Printer getPrinter() {
        return printer;
    }
}
