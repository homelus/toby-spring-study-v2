package jun.spring.ch1.enable.aspect;

import org.springframework.stereotype.Component;

@Component
public class TestService implements ITestService{

    @Override
    public void test() {
        System.out.println("test 실행");
    }

}
