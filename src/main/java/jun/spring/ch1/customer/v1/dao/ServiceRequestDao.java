package jun.spring.ch1.customer.v1.dao;

import jun.spring.ch1.customer.v1.model.ServiceRequest;
import jun.spring.ch1.customer.common.Customer;
import org.springframework.stereotype.Repository;

@Repository
public class ServiceRequestDao {

    public Customer findCustomerByNo(String customerNo) {
        return new Customer("123", "test@test.com");
    }

    public void add(ServiceRequest request, Customer customer) {
        System.out.println("Add Customer Service");
    }

}
