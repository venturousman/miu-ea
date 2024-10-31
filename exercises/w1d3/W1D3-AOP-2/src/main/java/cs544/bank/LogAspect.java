package cs544.bank;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import cs544.bank.logging.ILogger;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LogManager.getLogger(LogAspect.class.getName());
    @Autowired
    private ILogger logger2; // logger2 and logger also work

    @Before("execution(* cs544.bank.dao.*.*(..))")
    public void logDAOPackageMethods(JoinPoint joinpoint) {
        // logger.warn("In cs544.bank.dao package, method= " +
        // joinpoint.getSignature().getName());
        logger2.log("In cs544.bank.dao package, method= " +
                joinpoint.getSignature().getName());
    }

    @Around("execution(* cs544.bank.service.*.*(..))")
    public Object measureDurationServiceMethods(ProceedingJoinPoint call) throws Throwable {
        String method = call.getSignature().getName();
        StopWatch sw = new StopWatch();
        sw.start(method);

        Object retVal = call.proceed();

        sw.stop();
        long totaltime = sw.getTotalTimeMillis();

        // print the time to the console
        String message = String.format("Time to execute method %s = %s ms", method, totaltime);
        System.out.println(message);

        return retVal;
    }

    // Log every JMS message that is sent (using the Logger class)
    @After("execution(* cs544.bank.jms.JMSSender.sendJMSMessage(..))")
    public void logJMSMessageSent(JoinPoint joinpoint) {
        // logger.warn("JMS message was sent in method= " +
        // joinpoint.getSignature().getName());
        logger2.log("JMS message was sent in method= " +
                joinpoint.getSignature().getName());
    }

}
