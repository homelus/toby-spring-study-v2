package jun.spring.ch1.customer.common;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendMail(String email, String title) {
        System.out.println(email + " Email 전송 완료: " + title);
    }

}
