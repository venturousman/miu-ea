package cs544a;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAspect {
    @Before("execution(* cs544a.*.get*(..))")
    public void before(JoinPoint jp) {
        System.out.println("Before: " + jp.getSignature().getName()
                + " on: " + jp.getTarget().getClass().getSimpleName());
    }
}
