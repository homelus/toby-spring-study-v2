package jun.spring.ch1.web;

import jun.spring.ch1.customer.v3_objectFactory.controller.CustomerController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PROTOTYPE_WEB_V3_TEST.Config.class)
public class PROTOTYPE_WEB_V3_TEST {

    @Autowired
    private CustomerController customerController;

    @Test
    public void V3_고객_테스트() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setParameter("custno", "123");
        customerController.serviceRequestFormSubmit(request);
    }

    @Configuration
    @ComponentScan(basePackages = {"jun.spring.ch1.customer.common", "jun.spring.ch1.customer.v3_objectFactory"})
    public static class Config {}

}
