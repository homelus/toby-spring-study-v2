package jun.spring.ch1.ioc.POJO;

public class HelloFactory {

    private HelloFactory() {

    }

    public static Hello createHello() {
        Hello hello = new Hello();
        hello.setName("FactoryBean Hello");
        return hello;
    }

}
