package jun.spring.ch1.ioc.POJO;

import org.springframework.beans.factory.annotation.Autowired;

public class ComponentAutowiredFailComputer {

    @Autowired
    private Printer printer;
}
