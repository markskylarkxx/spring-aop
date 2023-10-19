package com.kceecodes.springaop.email;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import jakarta.mail.MessagingException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import jakarta.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
@Data
public class EmailAction extends AppenderBase<ILoggingEvent> {

    private  String smtpHost;
    private  int smtpPort;
    private  String username;
    private String password;
    private String to;
    private  String subject;

      @Autowired
      private JavaMailSender mailSender;

    @Override
    protected void append(ILoggingEvent event) {
        if (event.getLevel().toInt() == Level.ERROR_INT){
            //if log event is at the error event level send an email
               //sendEmail(event.getMessage());
        }

    }



    private  void sendEmail(String message){
        try{
            String subject =  "Error Message";
            String senderName= "USER REGISTRATION PORTAL SERVICE";
          //  String mailContent = "Hi " + user.getName()+   " Thank you for registering with us";
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            var messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("markskylarkxx@gmail.com", senderName);
            messageHelper.setTo("");
            messageHelper.setSubject(subject);
            // messageHelper.setText(mailContent, true);
          //  messageHelper.setText(EmailMessageUtil.getEmailMessage(name, token));
            mimeMessage.setText("Hello, this is an error message");
            mailSender.send(mimeMessage);



        }catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
}
