package cs544;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
// import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class LogAspect {
    private static final Logger logger = LogManager.getLogger(LogAspect.class.getName());

    @After("execution(* cs544.EmailSender.sendEmail(..))")
    public void logAfter(JoinPoint joinpoint) {
        var args = joinpoint.getArgs(); // Returns the arguments passed to real method as Object[]
        String email = "";
        String message = "";
        if (args != null && args.length > 1) {
            email = args[0].toString();
            message = args[1].toString();
        }

        String server = "";
        var target = joinpoint.getTarget(); // Returns the Object on which real method was / will be called
        if (target instanceof IEmailSender) {
            IEmailSender emailSender = (EmailSender) target;
            server = emailSender.getOutgoingMailServer();
        }

        logger.warn("method= " + joinpoint.getSignature().getName()
                + " address=" + email
                + " message= " + message
                + " \noutgoing mail server= " + server);
    }

    @Around("execution(* cs544.CustomerDAO.save(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());

        Object retVal = call.proceed();

        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();

        // print the time to the console
        String message = String.format("Time to execute save = %s ms", totaltime);
        // logger.warn(message);
        System.out.println(message);

        return retVal;
    }
}
