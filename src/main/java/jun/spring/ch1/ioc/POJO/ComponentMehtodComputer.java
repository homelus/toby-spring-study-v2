package jun.spring.ch1.ioc.POJO;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ComponentMehtodComputer {

    String name;

    ComponentPrinter componentPrinter;

    ComponentPrinter mainPrinter;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ComponentPrinter getComponentPrinter() {
        return componentPrinter;
    }

    @Resource
    public void setComponentPrinter(ComponentPrinter componentPrinter) {
        this.componentPrinter = componentPrinter;
    }

    public ComponentPrinter getMainPrinter() {
        return mainPrinter;
    }

    @Resource
    public void setMainPrinter(ComponentPrinter mainPrinter) {
        this.mainPrinter = mainPrinter;
    }
}
