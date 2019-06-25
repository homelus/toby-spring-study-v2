package jun.spring.mvc.data;

import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author playjun
 * @since 2019 05 31
 */

@Data
@ToString
public class ArgData {

    private String name;

    private int age;

    private ArgType type;

    private List<String> aliases;

    Map<String, String> maps;

}
