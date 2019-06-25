package jun.spring.etc;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.ToString;
import org.junit.Test;

import java.io.IOException;

/**
 * @author playjun
 * @since 2019 06 03
 */
public class ObjectMapperTest {

    @Test
    public void objectMapperTest() {
        ObjectMapper objectMapper = new ObjectMapper();
        String k = "{\"name\" : \"jun\"}";

        try {
            Person person = objectMapper.readValue(k, Person.class);
            System.out.println(person);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Data
    @ToString
    private static class Person {
        String name;
    }

}
