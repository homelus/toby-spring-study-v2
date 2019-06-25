package jun.spring.etc;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author playjun
 * @since 2019 06 10
 */
public class LocalDateTimeTest {

    @Test
    public void localDateTimeParseTest() {
        System.out.println(LocalDateTime.parse("20190610163100", DateTimeFormatter.ofPattern("yyyyMMddHHmmss")).toString());
    }

}
