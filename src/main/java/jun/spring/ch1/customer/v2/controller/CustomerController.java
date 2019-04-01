package jun.spring.ch1.customer.v2.controller;

import jun.spring.ch1.customer.v2.model.ServiceRequest;
import jun.spring.ch1.customer.v2.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {

    @Autowired
    ServiceRequestService serviceRequestService;

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/v2/customer", method = RequestMethod.GET)
    public void serviceRequestFormSubmit(HttpServletRequest request) {
        System.out.println("service controller v2 entrance, parameter: " + request.getParameter("custno"));
        ServiceRequest serviceRequest = applicationContext.getBean("serviceRequest", ServiceRequest.class);
        serviceRequest.setCustomerByCustomerNo(request.getParameter("custNo"));
        serviceRequestService.addNewServiceRequest(serviceRequest);

    }


}
