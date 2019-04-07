package jun.spring.etc.ioc.POJO;

public class Hello {

    String name;
    /**
     * 인터페이스를 두고 느슨히 연결
     */

    Printer printer;

    public String sayHello() {
        return "Hello " + name;
    }

    public void print() {
        this.printer.print(sayHello());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrinter(Printer printer) {
        this.printer = printer;
    }

    public Printer getPrinter() {
        return printer;
    }
}
