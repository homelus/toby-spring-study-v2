package jun.spring.ch1.ioc.POJO;

public class ConsolePrinter implements Printer {
    public void print(String message) {
        System.out.println(message);
    }
}
