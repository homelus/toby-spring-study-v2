package jun.spring.etc.ioc.POJO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

@Data
public class ComponentAutowiredQualifierComputer {

    @Autowired
    @Qualifier("mainPrinter")
    private Printer printer;

}
