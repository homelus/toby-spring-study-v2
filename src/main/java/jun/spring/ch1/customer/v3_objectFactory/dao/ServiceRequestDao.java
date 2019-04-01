package jun.spring.ch1.customer.v3_objectFactory.dao;

import jun.spring.ch1.customer.common.Customer;
import jun.spring.ch1.customer.v3_objectFactory.model.ServiceRequest;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceRequestDao {

    public Customer findCustomerByNo(String customerNo) {
        return new Customer(customerNo, "test@test.com");
    }

    public void add(ServiceRequest serviceRequest) {
        System.out.println("ServiceRequest ");
    }

}
