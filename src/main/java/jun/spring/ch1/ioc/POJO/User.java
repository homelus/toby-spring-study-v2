package jun.spring.ch1.ioc.POJO;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {

    @Value("${test.username}")
    String username;

    String password;

    @Value("#{systemProperties['os.name']}")
    String osName;

}
