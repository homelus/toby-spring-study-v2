package jun.spring.ch1.enable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAspect {

    @Around("execution(* jun.spring.ch1.enable.TestService.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("logging 시작: " + pjp.getSignature().getName());
        Object result = pjp.proceed();
        System.out.println("logging 종료: " + pjp.getSignature().getName());
        return result;
    }

}
