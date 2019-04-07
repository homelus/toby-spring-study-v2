package jun.spring.etc.ioc.POJO;

public class ConsolePrinter implements Printer {
    public void print(String message) {
        System.out.println(message);
    }
}
