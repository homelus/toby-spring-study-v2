package jun.spring.ch1.customer.v3_objectFactory.controller;

import jun.spring.ch1.customer.v3_objectFactory.model.ServiceRequest;
import jun.spring.ch1.customer.v3_objectFactory.service.ServiceRequestService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomerController {

    @Autowired
    ServiceRequestService serviceRequestService;

    @Resource
    private ObjectFactory<ServiceRequest> serviceRequestFactory;


    @RequestMapping(value = "/v3/customer", method = RequestMethod.GET)
    public void serviceRequestFormSubmit(HttpServletRequest request) {
        System.out.println("service controller v3 entrance, parameter: " + request.getParameter("custno"));
        ServiceRequest serviceRequest = this.serviceRequestFactory.getObject();
        serviceRequest.setCustomerByCustomerNo(request.getParameter("custNo"));
        serviceRequestService.addNewServiceRequest(serviceRequest);

    }


}
