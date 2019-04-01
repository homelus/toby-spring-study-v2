package jun.spring.ch1.customer.v4_serviceLocatorFactory.service;

import jun.spring.ch1.customer.v4_serviceLocatorFactory.dao.ServiceRequestDao;
import jun.spring.ch1.customer.v4_serviceLocatorFactory.model.ServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRequestService {

    @Autowired
    ServiceRequestDao serviceRequestDao;

    public void addNewServiceRequest(ServiceRequest serviceRequest) {
        serviceRequestDao.add(serviceRequest);
        serviceRequest.notifyServiceRequsetRegistration();
    }

}
