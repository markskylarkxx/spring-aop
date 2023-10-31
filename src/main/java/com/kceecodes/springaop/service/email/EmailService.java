package com.kceecodes.springaop.service.email;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private  static  Logger logger = LoggerFactory.getLogger(EmailService.class);
    @Autowired
    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String body){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(body);
        message.setTo(to);
        message.setSubject(subject);
        mailSender.send(message);







    }
}
