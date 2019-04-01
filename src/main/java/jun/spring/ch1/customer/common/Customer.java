package jun.spring.ch1.customer.common;

import lombok.AllArgsConstructor;

public class Customer {

    String customerNo;
    String email;
    public NotificationMethod serviceNotificationMethod = NotificationMethod.EMAIL;

    public Customer() {}

    public Customer(String customerNo, String email) {
        this.customerNo = customerNo;
        this.email = email;
    }

    public enum NotificationMethod {
        EMAIL
    }

    public String getCustomerNo() {
        return customerNo;
    }

    public void setCustomerNo(String customerNo) {
        this.customerNo = customerNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
