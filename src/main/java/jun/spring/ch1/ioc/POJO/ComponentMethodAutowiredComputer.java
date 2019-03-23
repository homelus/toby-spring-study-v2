package jun.spring.ch1.ioc.POJO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class ComponentMethodAutowiredComputer {

    Printer printer;

    @Autowired
    public void init(Printer printer) {
        this.printer = printer;
    }

}
