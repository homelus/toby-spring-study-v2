package jun.spring.ch1.profile.importPkg;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({DevConfig.class, QaConfig.class, RealConfig.class})
public class ImportMainConfig {
}
