package jun.spring.ch1.ioc.POJO;

public class ManyPrinter1 implements Printer {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
