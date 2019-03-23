package jun.spring.ch1.ioc.POJO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class ComponentConstructAutowiredComputer {

    @Autowired
    public ComponentConstructAutowiredComputer(Printer printer) {
        this.printer = printer;
    }

    private Printer printer;


}
