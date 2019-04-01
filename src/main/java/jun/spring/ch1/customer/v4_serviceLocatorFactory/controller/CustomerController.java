package jun.spring.ch1.customer.v4_serviceLocatorFactory.controller;

import jun.spring.ch1.customer.v4_serviceLocatorFactory.factory.ServiceRequestFactory;
import jun.spring.ch1.customer.v4_serviceLocatorFactory.model.ServiceRequest;
import jun.spring.ch1.customer.v4_serviceLocatorFactory.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {

    @Autowired
    ServiceRequestService serviceRequestService;

    @Autowired
    ServiceRequestFactory serviceRequestFactory;

    @RequestMapping(value = "/v4/customer", method = RequestMethod.GET)
    public void serviceRequestFormSubmit(HttpServletRequest request) {
        System.out.println("service controller v4 entrance, parameter: " + request.getParameter("custno"));
        ServiceRequest serviceRequest = this.serviceRequestFactory.getServiceRequest();
        serviceRequest.setCustomerByCustomerNo(request.getParameter("custNo"));
        serviceRequestService.addNewServiceRequest(serviceRequest);

    }


}
