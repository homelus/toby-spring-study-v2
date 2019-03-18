package jun.spring.ch1.ioc.POJO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Map;

@Data
public class ComponentAutowiredEtcComputer {

    @Autowired
    Collection<Printer> printerCollection;

    @Autowired
    Printer[] printerArray;

    @Autowired
    Map<String, Printer> printerMap;


}
