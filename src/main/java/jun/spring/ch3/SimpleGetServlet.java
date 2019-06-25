package jun.spring.ch3;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author playjun
 * @since 2019 06 10
 */
public class SimpleGetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");

        resp.getWriter().print("<HTML><BODY>");
        resp.getWriter().print("Hello " + name);
        resp.getWriter().print("</BODY></HTML>");
    }
}
