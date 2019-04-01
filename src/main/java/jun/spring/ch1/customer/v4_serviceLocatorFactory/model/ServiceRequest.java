package jun.spring.ch1.customer.v4_serviceLocatorFactory.model;

import jun.spring.ch1.customer.common.Customer;
import jun.spring.ch1.customer.common.EmailService;
import jun.spring.ch1.customer.v4_serviceLocatorFactory.dao.ServiceRequestDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * ServiceRequestService 에게 ServiceRequest 오브젝트에 담긴 서비스 요청 내역과 함께 Customer 를 전달 받게 끔한다.
 * 비즈니스 로직을 담당하는 코드가 휘둘리지 않게 독립적으로 만들어 준다.
 */
@Data
@Component
@Scope("prototype")
public class ServiceRequest {

    Customer customer; // customerNo 대신 customer 를 갖는다.
    String productNo;
    String description;

    @Autowired
    private ServiceRequestDao serviceRequestDao;

    @Autowired
    private EmailService emailService;

    public void setCustomerByCustomerNo(String customerNo) {
        this.customer = serviceRequestDao.findCustomerByNo(customerNo);
    }

    public void notifyServiceRequsetRegistration() {
        if (this.customer.serviceNotificationMethod == Customer.NotificationMethod.EMAIL) {
            this.emailService.sendMail(customer.getEmail(), ":A/S 정상 접수 처리");
        }
    }

}
