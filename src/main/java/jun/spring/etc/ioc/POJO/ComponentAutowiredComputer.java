package jun.spring.etc.ioc.POJO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("autowiredComputer")
public class ComponentAutowiredComputer {

    @Autowired
    private Printer fieldAutoiwredPrinter;

    private Printer methodAutoiwredPrinter;

    public Printer getFieldAutoiwredPrinter() {
        return fieldAutoiwredPrinter;
    }

    public void setFieldAutoiwredPrinter(Printer fieldAutoiwredPrinter) {
        this.fieldAutoiwredPrinter = fieldAutoiwredPrinter;
    }

    public Printer getMethodAutoiwredPrinter() {
        return methodAutoiwredPrinter;
    }

    @Autowired
    public void setMethodAutoiwredPrinter(Printer methodAutoiwredPrinter) {
        this.methodAutoiwredPrinter = methodAutoiwredPrinter;
    }
}
