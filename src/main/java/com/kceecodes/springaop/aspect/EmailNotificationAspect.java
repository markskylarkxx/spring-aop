package com.kceecodes.springaop.aspect;


import com.kceecodes.springaop.service.email.EmailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class EmailNotificationAspect {

    @Value("${admin.email}")
    private  String adminEmail;
    @Value("${admin.name}")
    private  String admin;

    @Autowired
    private EmailService service;

    private static final Logger logger = LoggerFactory.getLogger(EmailNotificationAspect.class);

    @AfterReturning(pointcut = "execution(* com.kceecodes.springaop.service.TransactionService.withdraw(com.kceecodes.springaop.model.Transaction))",
            returning = "result")
    public  void sendEmailNotification( JoinPoint point, Object result) {

          //todo; call the sendEmail fxn to send  a notification email to the admin
        service.sendEmail(adminEmail, "Transaction Notification", "The transaction was successful.");
        logger.info("Email notification sent to: " + admin);

    }
}
