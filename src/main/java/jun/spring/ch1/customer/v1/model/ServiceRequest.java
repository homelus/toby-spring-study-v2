package jun.spring.ch1.customer.v1.model;

import lombok.Data;

@Data
public class ServiceRequest {

    String customerNo;
    String productNo;
    String description;

}
