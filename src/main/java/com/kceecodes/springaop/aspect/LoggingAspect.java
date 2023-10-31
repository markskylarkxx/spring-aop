package com.kceecodes.springaop.aspect;

import com.kceecodes.springaop.exception.InsufficientBalanceException;
import com.kceecodes.springaop.util.UniqueKeyGenerator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private  static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;








    @Before("execution(* com.kceecodes.springaop.service.TransactionService.withdraw(..))")
    public static void before(JoinPoint joinPoint) {
        System.out.println("Before advice: Code to run before the target method");

         String methodName = joinPoint.getSignature().getName();
        System.out.println("Method name::: " + methodName);



    }
    @After("execution(* com.kceecodes.springaop.service.TransactionService.*(..))")
    public void after(JoinPoint joinPoint) {
         String methodName = joinPoint.getSignature().getName();


               //todo: Log to Redis-server
          String uniqueKey = UniqueKeyGenerator.generateUniqueKey();
          redisTemplate.opsForList().leftPush(uniqueKey, "withdrawal successful");
           // todo;   log to console and file;
       // logger.info("<><><><> " + uniqueKey, "withdrawal successful!");
    }



    @AfterReturning(pointcut = "execution(* com.kceecodes.springaop.service.TransactionService.withdraw(..))",
            returning = "returnValue")
    public void afterReturningMethod(Object returnValue) {
        System.out.println("After Returning advice: Code to run after the target method successfully returns");

               //  logger.info("withdraw successful......................................");
                // logger.warn("successful withdrawal");
    }



    // THIS METHOD  WILL ONLY RUN WHEN THERE IS AN EXCEPTION IN THE TARGET METHOD
    @AfterThrowing(pointcut = "execution(* com.kceecodes.springaop.service.TransactionService.withdraw(..))", throwing = "exception")
    public void afterThrowingMethod(JoinPoint joinPoint, Throwable exception) {

               //todo; this logs the exception generated to the console and file appender
      //  logger.info("Exception caught: " + exception.getMessage());

        System.out.println("Exception caught: " + exception.getMessage());


    }


    @Around("execution(* com.kceecodes.springaop.service.*.*(..))")
    public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around advice: Code to run around the target method (before and after)");

        // Get the method's signature (name)
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println(">>>>>>>>>> " + methodName);


        long startTime = System.currentTimeMillis();

        try {
            // Proceed with the method's execution
            Object result = joinPoint.proceed();

            // Log method exit and execution time
            long executionTime = System.currentTimeMillis() - startTime;
            System.out.println("Exiting method: " + methodName);
            System.out.println("Method execution time: " + executionTime + " ms");

            return result;
        } catch (Exception e) {
            // Log any exceptions that occur during method execution
            System.err.println("Exception in method: " + methodName);
            System.err.println("Exception details: " + e.getMessage());

            // Re-throw the exception to propagate it
            throw e;
        }


    }
}