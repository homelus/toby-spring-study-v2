package jun.spring.ch1.customer.v1.controller;

import jun.spring.ch1.customer.v1.model.ServiceRequest;
import jun.spring.ch1.customer.v1.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {

    @Autowired
    ServiceRequestService serviceRequestService;

    @RequestMapping(value = "/v1/customer", method = RequestMethod.GET)
    public void serviceRequestFormSubmit(HttpServletRequest request) {
        System.out.println("service controller entrance, parameter: " + request.getParameter("custno"));
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setCustomerNo(request.getParameter("custno"));
        serviceRequestService.addNewServiceRequest(serviceRequest);
    }

}
