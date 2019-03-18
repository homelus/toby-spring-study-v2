package jun.spring.ch1.ioc.POJO;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Data
@Component
public class ComponentFieldComputer {

    String name;

    @Resource
    private ComponentPrinter componentPrinter;

    @Resource//(name = "printer") 테스트 필요
    private ComponentPrinter mainPrinter;

}
