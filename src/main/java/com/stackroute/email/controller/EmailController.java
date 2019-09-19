package com.stackroute.email.controller;


import com.stackroute.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
    private EmailService emailService;

    @Autowired
    public EmailController( EmailService emailService) {
        this.emailService = emailService;
    }
}
