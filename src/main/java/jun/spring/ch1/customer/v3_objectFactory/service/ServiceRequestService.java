package jun.spring.ch1.customer.v3_objectFactory.service;

import jun.spring.ch1.customer.v3_objectFactory.dao.ServiceRequestDao;
import jun.spring.ch1.customer.v3_objectFactory.model.ServiceRequest;
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
