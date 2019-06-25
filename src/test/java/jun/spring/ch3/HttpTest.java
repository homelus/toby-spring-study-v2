package jun.spring.ch3;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author playjun
 * @since 2019 06 10
 */
public class HttpTest {

    @Test
    public void httpTest() throws ServletException, IOException {

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
        req.addParameter("name", "Spring");
        MockHttpServletResponse res = new MockHttpServletResponse();

        SimpleGetServlet servlet = new SimpleGetServlet();
        servlet.service(req, res);

        assertThat(res.getContentAsString(), is("<HTML><BODY>Hello Spring</BODY></HTML>"));
        assertThat(res.getContentAsString().contains("Hello Spring"), is(true));

    }

    @Test
    public void dispatcherServletTest() throws ServletException, IOException {
        ConfigurableDispatcherServlet servlet = new ConfigurableDispatcherServlet();

        servlet.setRelativeLocations("/spring-servlet.xml");
        servlet.init(new MockServletConfig("spring"));

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/hello");
        req.addParameter("name", "Spring");
        MockHttpServletResponse res = new MockHttpServletResponse();

        servlet.service(req, res);

        ModelAndView mav = servlet.getModelAndView();
        assertThat(mav.getViewName(), is("/WEB-INF/view/hello.jsp"));
        assertThat((String) mav.getModel().get("message"), is("Hello Spring"));

    }

}
