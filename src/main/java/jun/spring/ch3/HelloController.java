package jun.spring.ch3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author playjun
 * @since 2019 06 10
 */
public class HelloController implements Controller {

    @Autowired
    HelloSpring helloSpring;

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");

        String message = helloSpring.sayHello(name);

        Map<String, Object> model = new HashMap<>();
        model.put("message", message);

        return new ModelAndView("/WEB-INF/view/hello.jsp", model);
    }
}
