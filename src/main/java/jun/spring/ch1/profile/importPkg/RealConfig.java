package jun.spring.ch1.profile.importPkg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("real")
public class RealConfig {
}
