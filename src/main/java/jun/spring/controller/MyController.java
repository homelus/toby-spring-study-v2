package jun.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MyController {

    @GetMapping("/test")
    public void test() {
        System.out.println("Controller entrance");
    }

}
