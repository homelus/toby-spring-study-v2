package jun.spring.ch1.customer.v1.service;

import jun.spring.ch1.customer.common.EmailService;
import jun.spring.ch1.customer.v1.dao.ServiceRequestDao;
import jun.spring.ch1.customer.v1.model.ServiceRequest;
import jun.spring.ch1.customer.common.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceRequestService {

    @Autowired
    ServiceRequestDao serviceRequestDao;

    @Autowired
    EmailService emailService;

    /**
     * 관심사가 분리되지 않은 느낌.
     * Service 에 관련된 정보가 Service Request 와 Customer 에 나뉘어 있다.
     * 이를 합쳐보는 건 어떨까?
     */
    public void addNewServiceRequest(ServiceRequest serviceRequest) {
        // 서비스 로직에서 DAO 에 요청해 필요한 정보를 먼저 조회
        Customer customer = this.serviceRequestDao.findCustomerByNo(serviceRequest.getCustomerNo());
        // 실 서비스 로직 실행
        serviceRequestDao.add(serviceRequest, customer);
        // 추가 서비스 로직 실행
        emailService.sendMail(customer.getEmail(), "A/S 접수 정상 처리");
    }

}
