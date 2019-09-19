package com.stackroute.email.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.email.domain.AlertMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
//indicates it is a service layer
@Service
public class NotificationService
{
   @Value("${stakeholder.cgi}")
   String cgi;
    @Value("${stakeholder.nda}")
    String nda;
  @Value("${stakeholder.disaster}")
    String disaster;
    //It enables to send mails
    public JavaMailSender javaMailSender;
    //AMQP (Advanced Message Queuing Protocol) is the protocol used by RabbitMQ for messaging.
    public NotificationService()
    {

    }//        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.stackroute.kafkamailservice.config");
   private  EmailService emailService;

    @Autowired
    public NotificationService(JavaMailSender javaMailSender, EmailService emailService) {
        this.javaMailSender = javaMailSender;
        this.emailService = emailService;

    }
    //performing kafkaListener
    @KafkaListener(topics  = "email1", groupId = "json")
    public void notificationSender(AlertMail alertMail)throws MailException {
        ObjectMapper objectMapper= new ObjectMapper();

        System.out.println("hello" +alertMail);
        //send mail The class having to,from,cc,subject,text
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("n.nandasingh221@gmail.com");
      if(alertMail.clientName=="CGI") {
           simpleMailMessage.setTo(cgi);

      }
       else if(alertMail.getClientName().equals("NDA")) {
           simpleMailMessage.setTo(nda);
        }
      else  {
          simpleMailMessage.setTo(disaster);
     }
       simpleMailMessage.setFrom("succoursr@gmail.com");
        simpleMailMessage.setText("This the daily alert for CGI, Where in these observations were found as follows: " +
                "total No messages 1, positive count 1,positiveAverage 1.0,negativeCount 1.0," +
               "negativeAverage0.0,neutralCount 0,neutralAverage 0.0");


emailService.save(alertMail);
        //sending mail
        javaMailSender.send(simpleMailMessage);
    }
}