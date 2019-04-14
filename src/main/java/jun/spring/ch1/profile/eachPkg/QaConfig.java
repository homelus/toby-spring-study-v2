package jun.spring.ch1.profile.eachPkg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("qa")
public class QaConfig {

}
