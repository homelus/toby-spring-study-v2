package jun.spring.mvc.controller;

import jun.spring.mvc.annotation.NameValidation;
import jun.spring.mvc.data.Grade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    @RequestMapping("/test")
    public String test(@NameValidation String name, Grade grade) {
        System.out.println("Controller entrance: " + name);
        return "/WEB-INF/test1.jsp";
    }

    @RequestMapping("/school/{name}")
    public String schools(@PathVariable String name) {
        System.out.println("Controller schools: " + name);
        return "/WEB-INF/test1.jsp";
    }

    @RequestMapping("/school")
    public String traySchool(@RequestHeader String agent) {
        System.out.println("Controller agent: " + agent);
        return "/WEB-INF/test1.jsp";
    }

    @GetMapping("/gm/school")
    public String getMappingTest(String name) {
        System.out.println("Controller getMapping: " + name);
        return "/WEB-INF/test1.jsp";
    }

    @RequestMapping("/res/school")
    @ResponseBody
    public String resBody(String name) {
        System.out.println("Controller res body: " + name);
        return "/WEB-INF/test1.jsp";
    }

    @RequestMapping("/req/school")
    public String reqBody(@RequestBody String name) {
        System.out.println("Controller req body: " + name);
        return "/WEB-INF/test1.jsp";
    }

}
