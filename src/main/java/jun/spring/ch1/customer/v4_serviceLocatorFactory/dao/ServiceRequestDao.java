package jun.spring.ch1.customer.v4_serviceLocatorFactory.dao;

import jun.spring.ch1.customer.common.Customer;
import jun.spring.ch1.customer.v4_serviceLocatorFactory.model.ServiceRequest;
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
